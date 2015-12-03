package com.ecom.beans;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.servlet.ServletException;

import com.ecom.dao.CustomerDaoRemote;
import com.ecom.entities.Customer;

@Singleton
@LocalBean
@Startup
public class NewsLetterBean{

	@Resource
	private TimerService timerService;
	
	@EJB
	private MailSenderBean mailSender;
	
	@EJB
	private CustomerDaoRemote customerDao;

	@PostConstruct
	private void init() {

		TimerConfig timerConfig = new TimerConfig();

		timerConfig.setInfo("CalendarProgTimerDemo_Info");

		ScheduleExpression schedule = new ScheduleExpression();
		schedule.dayOfMonth(3).hour("17").minute("00").second("00");
		timerService.createCalendarTimer(schedule, timerConfig);
	}
	
	@Asynchronous
	@Timeout
	public void sendNewsLetter(Timer timer) throws ServletException, IOException{
		
		try{
			System.out.println("Sending newsletter : " + timer.getInfo());
			String fromEmail = "ecom.franois@gmail.com";
			String username = "ecom.franois";
			String password = "ecom1990";

			String subject = "MyStickIt : venez découvrir nos nouveautés !";
			String message = "Bonjour M/Mme, venez découvrir les dernières nouveautés sur vos site de création de stickers personnalisés préféré.";

			List<Customer> customerList = customerDao.list();
			Iterator<Customer> iter = customerList.iterator();
			while (iter.hasNext()) {
			    Customer customer = iter.next();
			    String toEmail = customer.getEmail();
			    
			    // Call to mail sender bean
				mailSender.sendEmail(fromEmail, username, password, toEmail, subject, message);
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}