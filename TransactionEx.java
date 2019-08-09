import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// JDBC 2.0 : excuteBatch()
// INSERT, UPDATE, DELETE, CREATE, DROP, ALTER 등에서만 사용할 수 있다.
public class TransactionEx {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean success = false;
		try {
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"scott",
					"tiger");
			con.setAutoCommit(false); // 오토 커밋을 실행하지 않고 롤백이 가능하게
//------------------------------------------------------ 		커밋 전까지의 실행을 한 묶음의 트랜잭션으로 처리 
			String sql = "insert into test3 values('홍길동','1111')";
			pstmt = con.prepareStatement(sql);
			pstmt.executeQuery();
			System.out.println("11111");

			sql = "insert into test3 values('전우치','2222')";
			pstmt = con.prepareStatement(sql);
			pstmt.executeQuery();
			System.out.println("22222");

			sql = "insert into test3 values('손오공','3333'"; // '3333'뒤의 ) 없애서 에러를 유도
			pstmt = con.prepareStatement(sql);
			pstmt.executeQuery();
			System.out.println("33333");

			success = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (success) {
					System.out.println("44444");
					con.commit();
				} else {
					System.out.println("55555");
					con.rollback();
				}

				if (pstmt != null)	pstmt.close();
				if (con != null)	con.close();
			} catch (SQLException sple) {
			}
		}
	}

}
