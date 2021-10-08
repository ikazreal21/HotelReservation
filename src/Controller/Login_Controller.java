package Controller;

import hotelgui_Views.LoginForm;

public class Login_Controller {
    
     public static Boolean login(){
       Boolean res;
       String password2 = LoginForm.password.getText();
       
       
       if(password2.contains("hotelsystem")){
           res = true;
       }
       else{
          res = false;
       }
       return res;
    }
}
