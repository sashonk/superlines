package superlines.server;

import java.sql.*;




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
	
	
	public void confirmAccount(final String uri){
		
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
			st.setString(2, "user");
			st.executeUpdate();
			
			
			st = conn.prepareStatement("insert into profiles (name, surname, crts, accountid, rankid) values (?, ? , CURRENT_TIMESTAMP, ?, 1)");
			st.setString(1, name);
			st.setString(2, surname);
			st.setString(3, login);
			st.executeUpdate();
			
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
