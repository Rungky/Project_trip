package service;

import java.util.ArrayList;
import java.util.List;

import dao.TripDAO;
import dto.QuestionDTO;



public class QnaService {
	TripDAO tripDAO;
	
	public QnaService() {
		tripDAO = new TripDAO();
	}
	
	List recursive(int pId, List list) {
		List resultList = new ArrayList();
		
		for(int i=0; i<list.size(); i++) {
			
			QuestionDTO dto = (QuestionDTO)list.get(i);
			if(dto.getQuestion_parentno() == pId) {
				resultList.add(dto);

				List tempList = recursive(dto.getQuestion_no(), list);
				resultList.add( tempList );
				
			}
		}
		
		return resultList;
	}
	
	public int getTotal() {
		return tripDAO.selectTotalQuestion();
	}
	
//	public List<QuestionDTO> listArticles(){
//		List<QuestionDTO> articlesList = tripDAO.selectAllQuestion();
//		
//		return articlesList;
//	}
	//부모 없는글
	public List<QuestionDTO> listArticles(String id){
		List<QuestionDTO> articlesList = tripDAO.selectMemberQuestion(id);
		
		return articlesList;
	}
	//답변글
	public List<QuestionDTO> listAnswers(){
		List<QuestionDTO> answersList = tripDAO.selectAnswer();
		
		return answersList;
	}
	
	public void addArticle(QuestionDTO questionDTO) {
		tripDAO.insertNewQuestion(questionDTO);
	}
	public void addReply(QuestionDTO questionDTO) {
		tripDAO.insertReplyQuestion(questionDTO);
	}
	
	public List<QuestionDTO> listQna(int question_no){
		List<QuestionDTO> QuestionList = tripDAO.selectQuestion(question_no);
		
		return QuestionList;
	}
	
	public List<QuestionDTO> ReplyQna(){
		List<QuestionDTO> QuestionList = tripDAO.selectReply();
		
		return QuestionList;
	}
	
	public void modArticle(QuestionDTO questionDTO) {
		tripDAO.updateArticle(questionDTO);
	}
	
	public List<QuestionDTO> listMod(int question_no){
		List<QuestionDTO> QuestionList = tripDAO.selectmodQuestion(question_no);
		
		return QuestionList;
	}
	
	public void removeArticle(int question_no) {
		tripDAO.deleteArticle(question_no);
	}
	
	//수정답변글 조회
	public List<QuestionDTO> listModreply(int question_no){
		List<QuestionDTO> answerList = tripDAO.selectmodReply(question_no);
		
		return answerList;
	}
	
	//수정부모글 조회
	public List<QuestionDTO> listArticles(int parentno){
		List<QuestionDTO> QuestionList = tripDAO.selectAllQuestion(parentno);		
		return QuestionList;
	}
	
	//수정답글
	public void modReply(QuestionDTO questionDTO) {
		tripDAO.updateReply(questionDTO);
	}
	
	//답글삭제
	public void removeReply(int question_no) {
		tripDAO.deleteReply(question_no);
	}
}
