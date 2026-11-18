package com.billing.otp.service;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.billing.BillingApplication;
import com.billing.common.BillingConstants;
import com.billing.common.service.serviceImpl.CommonServiceImpl;
import com.billing.otp.model.EmailDTO;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private CommonServiceImpl commonServ;
	

	public String sendOtp(String email, Integer otp, String name, String receiver_sms) {

		SimpleMailMessage message = new SimpleMailMessage();

		StringBuilder sbContent = new StringBuilder();
		sbContent.append("<body style=\"\r\n" + "      margin: 0;\r\n" + "      padding: 0;\r\n"
				+ "      font-family: Arial, sans-serif;\r\n" + "      background-color: #f4f6f9;\r\n" + "    \">\r\n"
				+ "  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"background-color: #f4f6f9\">\r\n"
				+ "    <tr>\r\n" + "      <td align=\"center\" style=\"padding: 20px 0\">\r\n"
				+ "        <table width=\"600\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"\r\n"
				+ "              width: 600px;\r\n" + "              max-width: 600px;\r\n"
				+ "              background-color: #ffffff;\r\n" + "              margin: 0 auto;\r\n"
				+ "              box-shadow: 0 3px 12px rgba(0, 0, 0, 0.12);\r\n" + "            \">\r\n"
				+ "          <tr>\r\n" + "            <td style=\"padding: 0\">\r\n"
				+ "              <table width=\"100%\" style=\"\r\n"
				+ "                    background-color: #073b8f;\r\n"
				+ "                    border-radius: 0px 0px 19px 19px;\r\n"
				+ "                    background: linear-gradient(\r\n" + "                      135deg,\r\n"
				+ "                      #becff1 0%,\r\n" + "                      #abc0e4 45%,\r\n"
				+ "                      #b4c5d3 100%\r\n" + "                    );\r\n"
				+ "                    border-radius: 0px 0px 19px 19px;\r\n" + "                  \">\r\n"
				+ "                <tr>\r\n"
				+ "                  <td align=\"center\" style=\"padding: 15px 20px 15px 20px\">\r\n"
				+ "                    <img src=\"https://webzent.com/wp-content/uploads/2026/04/32534542-01-1-2.svg\" alt=\"ACS Logo\" width=\"100\" style=\"\r\n"
				+ "                          width: 100px;\r\n" + "                          max-width: 100px;\r\n"
				+ "                          height: auto;\r\n" + "                          display: block;\r\n"
				+ "                          margin: 0 auto;\r\n" + "                        \" />\r\n"
				+ "                  </td>\r\n" + "                </tr>\r\n" + "              </table>\r\n"
				+ "            </td>\r\n" + "          </tr>\r\n" + "\r\n" + "          <tr>\r\n"
				+ "            <td style=\"padding: 40px 30px; background-color: #ffffff\">\r\n"
				+ "              <p style=\"\r\n" + "                    margin: 0 0 20px 0;\r\n"
				+ "                    color: #001540;\r\n" + "                    font-size: 16px;\r\n"
				+ "                    line-height: 1.5;\r\n" + "                  \">\r\n" + "                Dear\r\n"
				+ "                <span style=\"font-weight: 400; color: #001540\">\r\n" + "                  " + name
				+ "\r\n" + "                </span>\r\n" + "              </p>\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "              <p style=\"\r\n" + "                    margin: 0 0 18px 0;\r\n"
				+ "                    color: #222222;\r\n" + "                    font-size: 16px;\r\n"
				+ "                    line-height: 1.6;\r\n" + "                  \">\r\n" + "                "
				+ receiver_sms + "\r\n" + "              </p>\r\n" + "              <!-- OTP -->\r\n"
				+ "              <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                <tr>\r\n"
				+ "                  <td align=\"center\" style=\"text-align: center; padding: 10px 0 20px 0;\">\r\n"
				+ "                    <p style=\"\r\n" + "                margin: 0;\r\n"
				+ "                padding: 0;\r\n" + "                color: #001540;\r\n"
				+ "                font-size: 35px;\r\n" + "                line-height: 1.2;\r\n"
				+ "                font-weight: 600;\r\n" + "                text-align: center;\r\n"
				+ "                letter-spacing: 6px;\r\n" + "            \">\r\n" + "                      " + otp
				+ "\r\n" + "                    </p>\r\n" + "                  </td>\r\n" + "                </tr>\r\n"
				+ "              </table>\r\n" + "\r\n" + "              <p style=\"\r\n"
				+ "                    color: #222222;\r\n" + "                    font-size: 16px;\r\n"
				+ "                    line-height: 1.5;\r\n" + "                    max-width: 570px;\r\n"
				+ "                  \">\r\n" + "                If you have any questions, feel free to contact:\r\n"
				+ "                <a href=\"https://webzent.com\" style=\"color: #667eea; text-decoration: none\">Contact Us</a>.\r\n"
				+ "              </p>\r\n" + "\r\n" + "\r\n" + "            </td>\r\n" + "          </tr>\r\n" + "\r\n"
				+ "          <tr>\r\n" + "            <td style=\"\r\n"
				+ "                  background-color: #223f79;\r\n" + "                  padding: 20px 25px;\r\n"
				+ "                  border-radius: 19px 19px 0px 0px;\r\n" + "                \">\r\n"
				+ "              <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                <tr>\r\n" + "                  <td align=\"center\">\r\n"
				+ "                    <p style=\"\r\n" + "                          margin: 0;\r\n"
				+ "                          color: #ffffff;\r\n" + "                          font-size: 12px;\r\n"
				+ "                          line-height: 1.6;\r\n" + "                        \">\r\n"
				+ "                      Email:\r\n"
				+ "                      <a href=\"mailto:info@acsforu.com\" style=\"color: #8ab4ff; text-decoration: none\">\r\n"
				+ "                        sales@webzent.com\r\n" + "                      </a>\r\n" + "\r\n"
				+ "                      &nbsp; | &nbsp; Phone:\r\n"
				+ "                      <a href=\"tel:+18885311818\" style=\"color: #8ab4ff; text-decoration: none\">\r\n"
				+ "                        +1-(315) 215-0989\r\n" + "                      </a>\r\n"
				+ "                    </p>\r\n" + "\r\n" + "                    <!-- ADDRESS -->\r\n"
				+ "                    <p style=\"\r\n" + "                          margin: 5px 0 0 0;\r\n"
				+ "                          color: #ffffff;\r\n" + "                          font-size: 12px;\r\n"
				+ "                          line-height: 1.6;\r\n" + "                        \">\r\n"
				+ "                      4th Floor D-21, Sector 63, Noida, Uttar Pradesh, 201301\r\n"
				+ "                    </p>\r\n" + "\r\n" + "                    <!-- DIVIDER -->\r\n"
				+ "                    <table width=\"70%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin: 12px auto 0 auto\">\r\n"
				+ "                      <tr>\r\n" + "                        <td style=\"\r\n"
				+ "                              border-top: 1px solid #29416f;\r\n"
				+ "                              font-size: 1px;\r\n"
				+ "                              line-height: 1px;\r\n" + "                            \">\r\n"
				+ "                          &nbsp;\r\n" + "                        </td>\r\n"
				+ "                      </tr>\r\n" + "                    </table>\r\n" + "\r\n"
				+ "                  \r\n" + "                  </td>\r\n" + "                </tr>\r\n"
				+ "              </table>\r\n" + "            </td>\r\n" + "          </tr>\r\n"
				+ "        </table>\r\n" + "      </td>\r\n" + "    </tr>\r\n" + "  </table>\r\n" + "</body>");

		MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject("Verify OTP");
			mimeMessageHelper.setFrom(new InternetAddress("notifications@webzent.in", "Notification"));
			mimeMessageHelper.setTo(email);
			mimeMessageHelper.setText(sbContent.toString(), true);
			ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
			emailExecutor.execute(new Runnable() {
				public void run() {
					mailSender.send(mimeMessageHelper.getMimeMessage());
				}
			});
			emailExecutor.shutdown();
		} catch (MessagingException var15) {
			var15.printStackTrace();
		} catch (UnsupportedEncodingException var16) {
			var16.printStackTrace();
		}

		return "OTP sent successfully";
	}

	
	
	public String sendProjectEmail(EmailDTO emailDto) {

		SimpleMailMessage message = new SimpleMailMessage();

		StringBuilder sbContent = new StringBuilder();

		sbContent.append("\r\n" + "<head>\r\n" + "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Welcome to Client Project</title>\r\n" + "</head>\r\n" + "\r\n" + "<body style=\"\r\n"
				+ "    margin: 0;\r\n" + "    padding: 0;\r\n" + "    font-family: Arial, sans-serif;\r\n"
				+ "    background-color: #f4f6f9;\r\n" + "\">\r\n"
				+ "    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n"
				+ "        style=\"background-color: #f4f6f9;\">\r\n" + "        <tr>\r\n"
				+ "            <td align=\"center\" style=\"padding: 20px 0;\">   \r\n"
				+ "                <table width=\"600\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n"
				+ "                    style=\"\r\n" + "                        width: 600px;\r\n"
				+ "                        max-width: 600px;\r\n"
				+ "                        background-color: #ffffff;\r\n"
				+ "                        margin: 0 auto;\r\n"
				+ "                        box-shadow: 0 3px 12px rgba(0,0,0,0.12);\r\n"
				+ "                    \">       \r\n" + "                    <tr>\r\n"
				+ "                        <td style=\"padding: 0;\">\r\n"
				+ "                            <table width=\"100%\" \r\n"
				+ "                                style=\"\r\n"
				+ "                                    background-color: #073b8f;\r\n"
				+ "                                     border-radius:  0px 0px  19px 19px;\r\n"
				+ "                                    background: linear-gradient(\r\n"
				+ "                                        135deg,\r\n"
				+ "                                        #083184 0%,\r\n"
				+ "                                        #90abd6 45%,\r\n"
				+ "                                        #0878dc 100%\r\n"
				+ "                                    );\r\n"
				+ "                                 border-radius:  0px 0px  19px 19px;\r\n"
				+ "                                \">\r\n" + "                                <!-- LOGO -->\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td align=\"center\"\r\n"
				+ "                                        style=\"\r\n"
				+ "                                            padding: 15px 20px 15px 20px;\r\n"
				+ "                                        \">\r\n"
				+ "                                        <img src=\"https://webzent.com/wp-content/uploads/2026/04/32534542-01-1-2.svg\"\r\n"
				+ "                                            alt=\"ACS Logo\"\r\n"
				+ "                                            width=\"100\"\r\n"
				+ "                                            style=\"\r\n"
				+ "                                                width: 100px;\r\n"
				+ "                                                max-width: 100px;\r\n"
				+ "                                                height: auto;\r\n"
				+ "                                                display: block;\r\n"
				+ "                                                margin: 0 auto;\r\n"
				+ "                                            \">\r\n"
				+ "                                    </td>\r\n" + "                                </tr>\r\n"
				+ "                            </table>\r\n" + "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <!-- ================= BODY ================= -->\r\n"
				+ "                    <tr>\r\n" + "                        <td style=\"\r\n"
				+ "                            padding: 40px 30px;\r\n"
				+ "                            background-color: #ffffff;\r\n" + "                        \">\r\n"
				+ "                            <!-- GREETING -->\r\n" + "                            <p style=\"\r\n"
				+ "                                margin: 0 0 20px 0;\r\n"
				+ "                                color: #001540;\r\n"
				+ "                                font-size: 16px;\r\n"
				+ "                                line-height: 1.5;\r\n" + "                            \">\r\n"
				+ "                                Hi\r\n" + "                                <span style=\"\r\n"
				+ "                                    font-weight: 400;\r\n"
				+ "                                    color: #001540;\r\n" + "                                \">\r\n"
				+ "                                    " + emailDto.getCustomerName() + "\r\n"
				+ "                                </span>,\r\n" + "                            </p>\r\n"
				+ "                            <!-- WELCOME -->\r\n" + "                            <p style=\"\r\n"
				+ "                                margin: 0 0 20px 0;\r\n"
				+ "                                color: #1265bd;\r\n"
				+ "                                font-size: 20px;\r\n"
				+ "                                line-height: 1.5;\r\n"
				+ "                                font-weight: 600;\r\n" + "                            \">\r\n"
				+ "                                Welcome to Client Project!\r\n"
				+ "                            </p>\r\n" + "                            <!-- CONTENT -->\r\n"
				+ "                            <p style=\"\r\n"
				+ "                                margin: 0 0 18px 0;\r\n"
				+ "                                color: #222222;\r\n"
				+ "                                font-size: 16px;\r\n"
				+ "                                line-height: 1.6;\r\n" + "                            \">\r\n"
				+ "                                " + emailDto.getSms() + "\r\n"
				+ "                                <br><br>\r\n"
				+ "                                Portal Login: <a href=" + BillingConstants.CUSTOMER_LOGIN_URL
				+ " style=\"color: #1265bd;\">Login Here</a>\r\n" + "                            </p>\r\n"
				+ "                            <p style=\"\r\n"
				+ "                                margin: 0 0 18px 0;\r\n"
				+ "                                color: #222222;\r\n"
				+ "                                font-size: 16px;\r\n"
				+ "                                line-height: 1.6;\r\n" + "                            \">\r\n"
				+ "                                Please use the same email address you’ve been using to communicate with us to log in.\r\n"
				+ "                            </p>\r\n" + "                            <p style=\"\r\n"
				+ "                                margin: 0 0 24px 0;\r\n"
				+ "                                color: #222222;\r\n"
				+ "                                font-size: 16px;\r\n"
				+ "                                line-height: 1.6;\r\n" + "                            \">\r\n"
				+ "                                If you need any assistance, please feel free to <a href=\"https://webzent.com/\" target=\"_blank\">contact us.</a> \r\n"
				+ "                            </p>\r\n" + "                            <!-- SIGNATURE -->\r\n"
				+ "                            <p style=\"\r\n"
				+ "                                margin: 0 0 5px 0;\r\n"
				+ "                                color: #222222;\r\n"
				+ "                                font-size: 16px;\r\n"
				+ "                                line-height: 1.5;\r\n" + "                            \">\r\n"
				+ "                                Best regards,\r\n" + "                            </p>\r\n"
				+ "                            <p style=\"\r\n" + "                                margin: 0;\r\n"
				+ "                                color: #001540;\r\n"
				+ "                                font-size: 16px;\r\n"
				+ "                                line-height: 1.5;\r\n"
				+ "                                font-weight: 600;\r\n" + "                            \">\r\n"
				+ "                                Webzent Technology Team\r\n" + "                            </p>\r\n"
				+ "                        </td>\r\n" + "                    </tr>\r\n" + "                    <tr>\r\n"
				+ "                        <td style=\"\r\n"
				+ "                            background-color: #001540;\r\n"
				+ "                            padding: 20px 25px;\r\n"
				+ "                            border-radius: 19px 19px 0px 0px ;\r\n"
				+ "                        \">\r\n"
				+ "                            <table width=\"100%\" border=\"0\"\r\n"
				+ "                                cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td align=\"center\">\r\n" + "\r\n"
				+ "                                        <!-- EMAIL + PHONE -->\r\n"
				+ "                                        <p style=\"\r\n"
				+ "                                            margin: 0;\r\n"
				+ "                                            color: #ffffff;\r\n"
				+ "                                            font-size: 12px;\r\n"
				+ "                                            line-height: 1.6;\r\n"
				+ "                                        \">\r\n"
				+ "                                            Email:\r\n"
				+ "                                            <a href=\"mailto:" + emailDto.getUserEmail() + "\"\r\n"
				+ "                                                style=\"\r\n"
				+ "                                                    color: #8ab4ff;\r\n"
				+ "                                                    text-decoration: none;\r\n"
				+ "                                                \">\r\n"
				+ "                                                " + emailDto.getUserEmail() + "\r\n"
				+ "                                            </a>\r\n"
				+ "                                            &nbsp; | &nbsp;\r\n"
				+ "                                            Phone:\r\n"
				+ "                                            <a href=\"tel:+13152150989\"\r\n"
				+ "                                                style=\"\r\n"
				+ "                                                    color: #8ab4ff;\r\n"
				+ "                                                    text-decoration: none;\r\n"
				+ "                                                \">\r\n"
				+ "                                                +1-(315) 215-0989\r\n"
				+ "                                            </a>\r\n"
				+ "                                        </p>\r\n"
				+ "                                        <!-- ADDRESS -->\r\n"
				+ "                                        <p style=\"\r\n"
				+ "                                            margin: 5px 0 0 0;\r\n"
				+ "                                            color: #ffffff;\r\n"
				+ "                                            font-size: 12px;\r\n"
				+ "                                            line-height: 1.6;\r\n"
				+ "                                        \">\r\n"
				+ "                                            4th Floor D-21, Sector 63, Noida, Uttar Pradesh, 201301\r\n"
				+ "                                        </p>\r\n"
				+ "                                        <!-- DIVIDER -->\r\n"
				+ "                                        <table width=\"70%\" border=\"0\"\r\n"
				+ "                                            cellspacing=\"0\"\r\n"
				+ "                                            cellpadding=\"0\"\r\n"
				+ "                                            style=\"margin: 12px auto 0 auto;\">\r\n"
				+ "                                            <tr>\r\n"
				+ "                                                <td style=\"\r\n"
				+ "                                                    border-top: 1px solid #29416f;\r\n"
				+ "                                                    font-size: 1px;\r\n"
				+ "                                                    line-height: 1px;\r\n"
				+ "                                                \">\r\n"
				+ "                                                    &nbsp;\r\n"
				+ "                                                </td>\r\n"
				+ "                                            </tr>\r\n"
				+ "                                        </table>                             \r\n"
				+ "                                    </td>\r\n" + "                                </tr>\r\n"
				+ "                            </table>\r\n" + "                        </td>\r\n"
				+ "                    </tr>\r\n" + "                </table>\r\n" + "            </td>\r\n"
				+ "        </tr>\r\n" + "    </table>\r\n" + "</body>\r\n" + "\r\n" + "\r\n" + "");

		MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject(emailDto.getSubject());
			mimeMessageHelper.setFrom(new InternetAddress("notifications@webzent.in", "Notification"));
			mimeMessageHelper.setTo(emailDto.getCustomerEmail());
			
			String role = commonServ.getUserRole();
//			******************************** When admin logged-in get email only once ********************************************
			if(role.equals("ROLE_ADMIN")) {
				
				mimeMessageHelper.setCc(commonServ.getAdminEmail());
			}
			else {
				
				mimeMessageHelper.setCc(new String[] { emailDto.getUserEmail(), commonServ.getAdminEmail() });
			}
//			*********************************************************************************************************************
					
			mimeMessageHelper.setText(sbContent.toString(), true);
			ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
			emailExecutor.execute(new Runnable() {
				public void run() {
					mailSender.send(mimeMessageHelper.getMimeMessage());
				}
			});
			emailExecutor.shutdown();
		} catch (MessagingException var15) {
			var15.printStackTrace();
		} catch (UnsupportedEncodingException var16) {
			var16.printStackTrace();
		}

		return "Project created successfully";
	}

	
	
	public String sendReminder(EmailDTO emailDto) {

		SimpleMailMessage message = new SimpleMailMessage();

		StringBuilder sbContent = new StringBuilder();

		sbContent.append("<body style=\"\r\n"
				+ "    margin: 0;\r\n"
				+ "    padding: 0;\r\n"
				+ "    font-family: Arial, sans-serif;\r\n"
				+ "    background-color: #f4f6f9;\r\n"
				+ "\">\r\n"
				+ "    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n"
				+ "        style=\"background-color: #f4f6f9;\">\r\n"
				+ "        <tr>\r\n"
				+ "            <td align=\"center\" style=\"padding: 20px 0;\">   \r\n"
				+ "                <table width=\"600\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n"
				+ "                    style=\"\r\n"
				+ "                        width: 600px;\r\n"
				+ "                        max-width: 600px;\r\n"
				+ "                        background-color: #ffffff;\r\n"
				+ "                        margin: 0 auto;\r\n"
				+ "                        box-shadow: 0 3px 12px rgba(0,0,0,0.12);\r\n"
				+ "                    \">       \r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"padding: 0;\">\r\n"
				+ "                            <table width=\"100%\" \r\n"
				+ "                                style=\"\r\n"
				+ "                                    background-color: #073b8f;\r\n"
				+ "                                     border-radius:  0px 0px  19px 19px;\r\n"
				+ "                                    background: linear-gradient(\r\n"
				+ "                                        135deg,\r\n"
				+ "                                        #083184 0%,\r\n"
				+ "                                        #90abd6 45%,\r\n"
				+ "                                        #0878dc 100%\r\n"
				+ "                                    );\r\n"
				+ "                                 border-radius:  0px 0px  19px 19px;\r\n"
				+ "                                \">\r\n"
				+ "                                <!-- LOGO -->\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td align=\"center\"\r\n"
				+ "                                        style=\"\r\n"
				+ "                                            padding: 15px 20px 15px 20px;\r\n"
				+ "                                        \">\r\n"
				+ "                                        <img src=\"https://webzent.com/wp-content/uploads/2026/04/32534542-01-1-2.svg\"\r\n"
				+ "                                            alt=\"ACS Logo\"\r\n"
				+ "                                            width=\"100\"\r\n"
				+ "                                            style=\"\r\n"
				+ "                                                width: 100px;\r\n"
				+ "                                                max-width: 100px;\r\n"
				+ "                                                height: auto;\r\n"
				+ "                                                display: block;\r\n"
				+ "                                                margin: 0 auto;\r\n"
				+ "                                            \">\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </table>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <!-- ================= BODY ================= -->\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"\r\n"
				+ "                            padding: 40px 30px;\r\n"
				+ "                            background-color: #ffffff;\r\n"
				+ "                        \">\r\n"
				+ "                            <!-- GREETING -->\r\n"
				+ "                            <p style=\"\r\n"
				+ "                                margin: 0 0 12px 0;\r\n"
				+ "                                color: #001540;\r\n"
				+ "                                font-size: 16px;\r\n"
				+ "                                line-height: 1;\r\n"
				+ "                            \">\r\n"
				+ "                                Hi\r\n"
				+ "                                <span style=\"\r\n"
				+ "                                    font-weight: 400;\r\n"
				+ "                                    color: #001540;\r\n"
				+ "                                \">\r\n"
				+ "                                     <b>" + emailDto.getCustomerName() + "</b>\r\n"
				+ "                                </span>,\r\n"
				+ "                            </p>\r\n"
				+ "                            <!-- WELCOME -->\r\n"
				+ "                           \r\n"
				+ "                            <!-- CONTENT -->\r\n"
				+ "                            <p style=\"\r\n"
				+ "                                margin: 0 0 12px 0;\r\n"
				+ "                                color: #222222;\r\n"
				+ "                                font-size: 16px;\r\n"
				+ "                                line-height: 1.2;\r\n"
				+ "                            \">\r\n"
				+ "                                This is a quick reminder to access your client portal to view your latest Project login details.\r\n"
				+ "                                <br><br>\r\n"
				+ "                                Portal Login: <a href=\"" +BillingConstants.CUSTOMER_LOGIN_URL+"\" style=\"color: #1265bd;\" >Login Here</a>\r\n"
				+ "                            </p>\r\n"
				+ "                           \r\n"
				+ "                            <p style=\"\r\n"
				+ "                                margin: 0 0 1px 0;\r\n"
				+ "                                color: #222222;\r\n"
				+ "                                font-size: 16px;\r\n"
				+ "                                line-height: 1.6;\r\n"
				+ "                            \">\r\n"
				+ "                                Please let us know if you need any assistance.<a href=\"https://webzent.com/\" target=\"_blank\">contact us.</a> \r\n"
				+ "                            </p>\r\n"
				+ "                            <!-- SIGNATURE -->\r\n"
				+ "                           \r\n"
				+ "                            \r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"\r\n"
				+ "                            background-color: #001540;\r\n"
				+ "                            padding: 20px 25px;\r\n"
				+ "                            border-radius: 19px 19px 0px 0px ;\r\n"
				+ "                        \">\r\n"
				+ "                            <table width=\"100%\" border=\"0\"\r\n"
				+ "                                cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td align=\"center\">\r\n"
				+ "\r\n"
				+ "                                        <!-- EMAIL + PHONE -->\r\n"
				+ "                                        <p style=\"\r\n"
				+ "                                            margin: 0;\r\n"
				+ "                                            color: #ffffff;\r\n"
				+ "                                            font-size: 12px;\r\n"
				+ "                                            line-height: 1.6;\r\n"
				+ "                                        \">\r\n"
				+ "                                            Email:\r\n"
				+ "                                            <a href=\"mailto:"+emailDto.getUserEmail()+"\"\r\n"
				+ "                                                style=\"\r\n"
				+ "                                                    color: #8ab4ff;\r\n"
				+ "                                                    text-decoration: none;\r\n"
				+ "                                                \">\r\n"
				+ "                                                "+emailDto.getUserEmail()+"\r\n"
				+ "                                            </a>\r\n"
				+ "                                            &nbsp; | &nbsp;\r\n"
				+ "                                            Phone:\r\n"
				+ "                                            <a href=\"tel:+13152150989\"\r\n"
				+ "                                                style=\"\r\n"
				+ "                                                    color: #8ab4ff;\r\n"
				+ "                                                    text-decoration: none;\r\n"
				+ "                                                \">\r\n"
				+ "                                                +1-(315) 215-0989\r\n"
				+ "                                            </a>\r\n"
				+ "                                        </p>\r\n"
				+ "                                        <!-- ADDRESS -->\r\n"
				+ "                                        <p style=\"\r\n"
				+ "                                            margin: 5px 0 0 0;\r\n"
				+ "                                            color: #ffffff;\r\n"
				+ "                                            font-size: 12px;\r\n"
				+ "                                            line-height: 1.6;\r\n"
				+ "                                        \">\r\n"
				+ "                                            4th Floor D-21, Sector 63, Noida, Uttar Pradesh, 201301\r\n"
				+ "                                        </p>\r\n"
				+ "                                        <!-- DIVIDER -->\r\n"
				+ "                                        <table width=\"70%\" border=\"0\"\r\n"
				+ "                                            cellspacing=\"0\"\r\n"
				+ "                                            cellpadding=\"0\"\r\n"
				+ "                                            style=\"margin: 12px auto 0 auto;\">\r\n"
				+ "                                            <tr>\r\n"
				+ "                                                <td style=\"\r\n"
				+ "                                                    border-top: 1px solid #29416f;\r\n"
				+ "                                                    font-size: 1px;\r\n"
				+ "                                                    line-height: 1px;\r\n"
				+ "                                                \">\r\n"
				+ "                                                    &nbsp;\r\n"
				+ "                                                </td>\r\n"
				+ "                                            </tr>\r\n"
				+ "                                        </table>                             \r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </table>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                </table>\r\n"
				+ "            </td>\r\n"
				+ "        </tr>\r\n"
				+ "    </table>\r\n"
				+ "</body>");

		MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject(emailDto.getSubject());
			mimeMessageHelper.setFrom(new InternetAddress("notifications@webzent.in", "Notification"));
			mimeMessageHelper.setTo(emailDto.getCustomerEmail());
			
			String role = commonServ.getUserRole();
//			******************************** When admin logged-in get email only once ********************************************
			if(role.equals("ROLE_ADMIN")) {
				mimeMessageHelper.setCc(commonServ.getAdminEmail());
			}
			else {
				mimeMessageHelper.setCc(new String[] { emailDto.getUserEmail(), commonServ.getAdminEmail() });
			}
//			*********************************************************************************************************************
			
			mimeMessageHelper.setText(sbContent.toString(), true);
			ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
			emailExecutor.execute(new Runnable() {
				public void run() {
					mailSender.send(mimeMessageHelper.getMimeMessage());
				}
			});
			emailExecutor.shutdown();
		} catch (MessagingException var15) {
			var15.printStackTrace();
		} catch (UnsupportedEncodingException var16) {
			var16.printStackTrace();
		}

		return "Project Reminder Sent Successfully";
	}

}











