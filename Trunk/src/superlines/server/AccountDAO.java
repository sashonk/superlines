package superlines.server;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import superlines.Configuration;
import superlines.server.mail.MailHelper;




public class AccountDAO extends DAO{
	
	private static AccountDAO instance;
	
	public static AccountDAO get(){
		if(instance == null){
			instance = new AccountDAO();
		}
		
		return instance;
	}
	
	
	public boolean isUniqueLogin(final String login) throws Exception {
		
		if(login==null){
			throw new NullPointerException("login==null");
		}
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = m_dataSource.getConnection();
			
			st = conn.prepareStatement("select count(0) c from users where user_name = ?"); 
			st.setString(1, login);
			
			rs = st.executeQuery();
			rs.next();

			int count = rs.getInt("c");
			return count == 0;
			
			
		}

		finally{
				try{
					if(st!=null){
						st.close();
					}
					if(conn!=null){
						conn.close();
					}
				}catch(Exception ex){
					m_log.error(ex);
				
				}
		}
	}
	
	/**
	 * 
	 * @param token
	 * @return 0 - account confirmed, 1 - application not found, 2 - other
	 * @throws Exception
	 */
	public int confirmAccount(final String token) throws Exception{
		Connection conn = null;
		PreparedStatement st = null;
		
		int exitStatus = 0;
		try{
			conn = m_dataSource.getConnection();
			conn.setAutoCommit(false);
			
			st = conn.prepareStatement("select accountid accid from applications where token = ?");			
			st.setString(1, token);					
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				String accountid = rs.getString("accid");
				
				st.close();
				
				st = conn.prepareStatement("update user_roles set role_name = ? where user_name = ?");
				st.setString(1, "user");
				st.setString(2, accountid);
				st.executeUpdate();
				st.close();

				exitStatus = 0;
			}
			else{
				exitStatus =  1;
			}
			
			
			conn.commit();			

		}
		catch(Exception ex){
			conn.rollback();
			m_log.error(ex);
			throw(ex);
		}

		finally{
				try{
					if(st!=null){
						st.close();
					}
					if(conn!=null){
						conn.close();
					}
				}catch(Exception ex){
					m_log.error(ex);
				
				}
		}
		
		return exitStatus;
	}
	
	public void createAccount(final String login, final String password, final String name, final String surname, final String email) throws Exception{
		Connection conn = null;
		PreparedStatement st = null;
		try{
			conn = m_dataSource.getConnection();
			conn.setAutoCommit(false);
			
			st = conn.prepareStatement("insert into users (user_name, user_password) values (?, ?)");			
			st.setString(1, login);
			st.setString(2, password);			
			st.executeUpdate();
			
			st = conn.prepareStatement("insert into user_roles (user_name, role_name) values (?, ?)");
			st.setString(1, login);
			st.setString(2, "unconfirmed");
			st.executeUpdate();
			
			
			st = conn.prepareStatement("insert into profiles (name, surname, crts, accountid, rankid) values (?, ? , CURRENT_TIMESTAMP, ?, 1)");
			st.setString(1, name);
			st.setString(2, surname);
			st.setString(3, login);
			st.executeUpdate();
			
			
			UUID token = UUID.randomUUID();
			st = conn.prepareStatement("insert into applications (accountid, token, crts) values (?, ?, CURRENT_TIMESTAMP)");
			st.setString(1, login);
			st.setString(2, token.toString());
			st.executeUpdate();
			

			String uri =  String.format("%s&token=%s", Configuration.get().getProperty("confirmation.url"), token) ;			
			String body = String.format("Для подтверждения аккаунта пройдите по ссылке \n %s", uri);
			List<String> recipients = new LinkedList<>();
			recipients.add(email);
			MailHelper.mail("Подтверждение аккаунта", body, recipients);
			
			conn.commit();
		}
		catch(Exception ex){
			conn.rollback();
			m_log.error(ex);
			throw(ex);
		}

		finally{
				try{
					if(st!=null){
						st.close();
					}
					if(conn!=null){
						conn.close();
					}
				}catch(Exception ex){
					m_log.error(ex);
				
				}
		}
	}
	

	private AccountDAO(){}
}
