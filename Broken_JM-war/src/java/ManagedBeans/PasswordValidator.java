/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author umangjain
 */
@FacesValidator("passvalidator")
public class PasswordValidator implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent c, Object val) throws ValidatorException {
       String password = (String) val;
        boolean good = true;
        int count1=0,count2=0;
        for(int i=0; i<password.length(); i++){
            char ch = password.charAt(i);
            if(ch >= 65 && ch <= 90) {
                    count1+=1;
            }
            if((ch >= 35 && ch <= 38)||ch==64) {
                    count2+=1;
            }
        }
        if(count1==0 || count2==0 || password.length()<8){
            good = false;
        }
        if (!good) {
            FacesMessage message = new FacesMessage();
            message.setDetail("Please enter a valid password");
            message.setSummary("Password not valid. The password must contain atleast one - Uppercase character||A special character(@,#,$,&,%)||Minimum length of 8 characters");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } 
    }
    
}
