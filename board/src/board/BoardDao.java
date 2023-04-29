package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.JdbcUtil;

public class BoardDao {
	private JdbcUtil ju;

	//삽입(C)
	public int insert(BoardVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO BOARD(\r\n " + 
				"NUM, TITLE, WRITER, CONTENT, REGDATE, CNT)\r\n" + 
				"VALUES(board_seq.nextval, '제목1', '작성자1', '내용1', SYSDATE, 0)";

		int re = -1;
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(1, vo.getContent());
			re = pstmt.executeUpdate();

			return re;
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if (con != null) {
				try {
					con.close(); //풀에 반환(커넥션은 풀에서 가져온거니까)
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close(); //풀에 반환(커넥션은 풀에서 가져온거니까)
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return re;
	}

	//조회(R)
	public List<BoardVo> selectAll(){
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql = "SELECT\r\n " + 
				"NUM, TITLE, WRITER, CONTENT, REGDATE, CNT FROM BOARD";
		ArrayList<BoardVo> ls = new ArrayList<BoardVo>();
		try {
			con = ju.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BoardVo vo = new BoardVo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						new Date(rs.getDate(5).getTime()),
						rs.getInt(6));

				ls.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close(); //풀에 반환(커넥션은 풀에서 가져온거니까)
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close(); //풀에 반환(커넥션은 풀에서 가져온거니까)
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close(); //풀에 반환(커넥션은 풀에서 가져온거니까)
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return ls;
	}
	//1개만 조회(R)
	public BoardVo selectOne(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = "SELECT\r\n " + 
				"NUM, TITLE, WRITER, CONTENT, REGDATE, CNT FROM BOARD WHERE NUM=?";
		BoardVo vo = null;
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
				if(rs.next()) {
					vo = new BoardVo(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							new Date(rs.getDate(5).getTime()),
							rs.getInt(6));
				}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close(); //풀에 반환(커넥션은 풀에서 가져온거니까)
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close(); //풀에 반환(커넥션은 풀에서 가져온거니까)
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close(); //풀에 반환(커넥션은 풀에서 가져온거니까)
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return vo;
	}

	//수정(U)

	//삭제(D)
}
