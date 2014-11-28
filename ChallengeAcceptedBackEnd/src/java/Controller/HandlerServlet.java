/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AAprojectchallenges;
import Model.AAprojectusers;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author michaef
 */
@WebServlet(name = "HandlerServlet", urlPatterns = {"/HandlerServlet"})
public class HandlerServlet extends HttpServlet {


    
  public ControlBean control;
  public DataHandler dataHandler;
  private String msg, newMsg;

    
  Boolean check;
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    //    setMsg(request.getParameter("test"));
//      check  = control.isDataChecker();
     //   response.setContentLength(32);
        response.setContentType("text/html");
        control = new ControlBean();
        String method = request.getParameter("method");
        dataHandler = new DataHandler();
        PrintWriter out = response.getWriter();
       
        
        if(method.contentEquals("addChallenge")){        
           
        
        response.addHeader("backEnd", "SUCCESS");
        dataHandler.setName(request.getParameter("name"));
        dataHandler.setDescription(request.getParameter("description"));
        dataHandler.setPoints(Integer.parseInt(request.getParameter("points")));
        dataHandler.addChallenges();
        
        setMsg(dataHandler.getName());
        }
       
       else if(method.contentEquals("findChallenge")){
           dataHandler.setId(Integer.parseInt(request.getParameter("id")));
           dataHandler.selectChallenge();
           response.addHeader("backEnd", "SUCCESS!");
           response.addHeader("cName", dataHandler.getName());
           response.addHeader("cDesc", dataHandler.getDescription());
           response.addHeader("cPoints", Integer.toString(dataHandler.getPoints()));
           System.out.print(response.containsHeader("cName"));
       }
       else if(method.contentEquals("allChallenges")){
            List<AAprojectchallenges> list = new ArrayList<AAprojectchallenges>();
           list = dataHandler.getListChallenges();
              response.addHeader("backEnd", "SUCCESS!");
              response.addHeader("challenges", "challenges");
              response.addHeader("itemCount", Integer.toString(list.size()));
           for(int i = 0; i < list.size(); i++){
              AAprojectchallenges item = new AAprojectchallenges();
              item = list.get(i);

               response.addHeader("cId" +Integer.toString(i), Integer.toString(item.getId())); 
               response.addHeader("cName" +Integer.toString(i) , item.getName());
              response.addHeader("cDesc" +Integer.toString(i) , item.getDescription());
              response.addHeader("cPoints" +Integer.toString(i), Integer.toString(item.getPoints())); 
                System.out.print(response.containsHeader("cName"+Integer.toString(i)));    
           }
           
           
       }
              else if(method.contentEquals("allUsers")){
            List<AAprojectusers> list = new ArrayList<AAprojectusers>();
           list = dataHandler.getListUsers();
           response.addHeader("backEnd", "SUCCESS!");
           response.addHeader("users", "users");
            response.addHeader("itemCount", Integer.toString(list.size()));
            System.out.print(Integer.toString(list.size()));
           for(int i = 0; i < list.size(); i++){
              AAprojectusers item = new AAprojectusers();
              item = list.get(i);
              
              
               response.addHeader("uId" +Integer.toString(i), Integer.toString(item.getId())); 
               response.addHeader("uTotPoints" +Integer.toString(i), Integer.toString(item.getTotalPoints())); 
               response.addHeader("uCurrentChallenge" +Integer.toString(i), Integer.toString(item.getCurrentChallenge())); 
               response.addHeader("uName" +Integer.toString(i) , item.getName());
              response.addHeader("uNotif" +Integer.toString(i) , Boolean.toString(item.getNotified()));
              response.addHeader("uActive" +Integer.toString(i) , Boolean.toString(item.getChallengeActive()));
              
                System.out.print(response.containsHeader("uName"+Integer.toString(i)));    
           }
           
           
       }
       
       
       
       else if(method.contentEquals("challengeUser")){
           dataHandler.setuId(Integer.parseInt(request.getParameter("User")));
           dataHandler.setChallId(Integer.parseInt(request.getParameter("Challenge")));
           
           dataHandler.challengeUser();
           response.addHeader("backEnd", "SUCCESS!");
               response.addHeader("trueStory", "Challenge SENT");
           }
       
       else if(method.contentEquals("checkIfChallenged")){
           response.addHeader("backEnd", "SUCCESS!");
           dataHandler.setuId(Integer.parseInt(request.getParameter("User")));
           dataHandler.challengeChecker();
           
           
           String userName = dataHandler.getUserName();
           response.addHeader("challChecker", "challChecker");
           response.addHeader("checkUser", userName);
           response.addHeader("checker", Boolean.toString(dataHandler.getChallengeActive()));
           System.out.print(response.containsHeader("challChecker"));    
       }
       else if(method.contentEquals("completeChallenge")){
           response.addHeader("backEnd", "SUCCESS!");
           dataHandler.setuId(Integer.parseInt(request.getParameter("User")));
           dataHandler.completeChallenge();
           response.addHeader("completed", "Challenge Completed");

       }
       else if(method.contentEquals("challengeResponse")){
             response.addHeader("backEnd", "SUCCESS!");
           if(request.getParameter("status").contentEquals("1")){
               dataHandler.setuId(Integer.parseInt(request.getParameter("User")));
               dataHandler.challengeAccepter();
               response.addHeader("trueStory", "Challenge ACCEPTED");
           }
           else{
               dataHandler.challengeRejecter();
               response.addHeader("trueStory", "Challenge REJECTED");
           }
          
               
       }
       else{
           
           System.out.println("NO POST RESPONSE!");
       }
       
       
       
       
       
      //setMsg("http://localhost:8080/ChallengeAcceptedBackEnd/");
        
      


        }
//        else if(msg.equals("check")){
//            if(check == true){
//                String replyToTest = "POLO FROM DEVICE A";
//                response.addHeader("msg", replyToTest); 
//            }
//            else{
//            String replyToTest = "NO MARCO RECEIVED BEFORE";
//            response.addHeader("msg", replyToTest);
//            }
//        }
 
                 
                 

    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

    








    public String getMsg() {



        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    public String getNewMsg() {
        return newMsg;
    }

    public void setNewMsg(String newMsg) {
        this.newMsg = newMsg;
    }
    
        @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
