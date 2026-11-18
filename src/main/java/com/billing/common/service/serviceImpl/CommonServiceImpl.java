package com.billing.common.service.serviceImpl;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.billing.common.BillingConstants;
import com.billing.common.service.CommonService;
import com.billing.company.model.CompanyInfo;
import com.billing.company.repository.CompanyInfoRepository;
import com.billing.customer.model.CustomerModel;
import com.billing.customer.repository.CustomerRepository;
import com.billing.login.model.User;
import com.billing.login.repository.UserRepository;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CompanyInfoRepository companyInfoRepository;

	@Override
	public MediaType contentType(String keyname) {
		String[] arr = keyname.split("\\.");
		String type = arr[arr.length - 1];
		switch (type) {
		case "txt":
			return MediaType.TEXT_PLAIN;
		case "png":
			return MediaType.IMAGE_PNG;
		case "jpg":
			return MediaType.IMAGE_JPEG;
		case "pdf":
			return MediaType.APPLICATION_PDF;
		default:
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}

	@Override
	public String getClientOS(String lowerCaseBrowser, String browserDetails) {
		if (lowerCaseBrowser.contains("windows")) {
			return "Windows";
		} else if (lowerCaseBrowser.contains("mac")) {
			return "Mac";
		} else if (lowerCaseBrowser.contains("x11")) {
			return "Unix";
		} else if (lowerCaseBrowser.contains("android")) {
			return "Android";
		} else if (lowerCaseBrowser.contains("iphone")) {
			return "IPhone";
		} else {
			return "UnKnown, More-Info: " + browserDetails;
		}
	}

	@Override
	public String getClientBrowser(String user, String browserDetails) {
		String browser = "";

		// ===============Browser===========================
		if (user.contains("msie")) {
			String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera"))
				browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
			else if (user.contains("opr"))
				browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
						.replace("OPR", "Opera");
		} else if (user.contains("chrome")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
				|| (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			browser = "IE";
		} else {
			browser = "UnKnown, More-Info: " + browserDetails;
		}

		return browser;
	}

	@Autowired
	CustomerRepository customerRepo;

	@Override
	public Object findByUsername(String username) {

//		return userRepository.findByUsername(name).orElseThrow(
//				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + name));		

		Optional<User> user = userRepository.findByUsername(username);

		if (user.isPresent()) {
			return user.get();
		}

		Optional<CustomerModel> customer = customerRepo.findByEmail(username);

		if (customer.isPresent()) {
			return customer.get();
		}

		throw new UsernameNotFoundException("User Not Found : " + username);
	}

	@Override
	public String getDocumentActaulNumber(int maxNo, int companyId, String fy) {
		int maxNum = maxNo + 1;
		StringBuilder actualNo = new StringBuilder();
		StringBuilder maxNumber = new StringBuilder();
		CompanyInfo company = companyInfoRepository.getOne(companyId);
		String[] arrOfCompanyName = company.getCompanyName().trim().split("\\s");
		for (String str : arrOfCompanyName) {
			actualNo.append(str.toUpperCase().charAt(0));
		}
		System.out.println(companyId + "----" + actualNo);
		actualNo.append("/").append(fy).append("/");
		if (maxNum == 0) {
			maxNumber.append("0001");
		} else if (maxNum < 10) {
			maxNumber.append("000" + maxNum);
		} else if (maxNum < 100) {
			maxNumber.append("00" + maxNum);
		} else if (maxNum < 1000) {
			maxNumber.append("0" + maxNum);
		} else {
			maxNumber.append(maxNum);
		}
		actualNo.append(maxNumber);
		return actualNo.toString();
	}

	@Override
	public int getCurrentYear() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return year;
	}

	@Override
	public int getCurrentMonth() {
		int month = (Calendar.getInstance().get(Calendar.MONTH) + 1);
		return month;
	}

	@Override
	public String setFYKeyword(int currentYear, int currentMonth) {
		String financiyalYearKeyword = "";
		if (currentMonth < 4) {
			financiyalYearKeyword = (currentYear - 1 + "").substring(2) + "" + (currentYear + "").substring(2);
		} else {
			financiyalYearKeyword = (currentYear + "").substring(2) + "" + (currentYear + 1 + "").substring(2);
		}
		return financiyalYearKeyword;
	}

	@Override
	public int getUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByUsername(auth.getName()).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + auth.getName()));
		Long userId = user.getId();
		return userId.intValue();
	}

	@Override
	public String getMonthName(int monthId) {
		String monthName = "";
		switch (monthId) {
		case 1:
			monthName = "January";
			break;
		case 2:
			monthName = "February";
			break;
		case 3:
			monthName = "March";
			break;
		case 4:
			monthName = "April";
			break;
		case 5:
			monthName = "May";
			break;
		case 6:
			monthName = "June";
			break;
		case 7:
			monthName = "July";
			break;
		case 8:
			monthName = "August";
			break;
		case 9:
			monthName = "September";
			break;
		case 10:
			monthName = "October";
			break;
		case 11:
			monthName = "November";
			break;
		case 12:
			monthName = "December";
			break;
		}
		return monthName;
	}

//	****************************** GET ADMIN EMAIL ********************************************************
	@Override
	public String getAdminEmail() {
		if (BillingConstants.ServerCode) {
			return BillingConstants.adminEmail;
		} else {
			return BillingConstants.LocalTestEmail;
		}
	}
	

//******************************** GET LOGGED-IN USER'S ROLE ***********************************************************************
	@Override
	public String getUserRole() {

		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).findFirst().orElse(null);
		return role;
	}
	
	
	//******************************** GET LOGGED-IN USER ***********************************************************************
	public User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) this.userRepository.findByUsername(auth.getName()).orElseThrow(() -> {
			return new UsernameNotFoundException("User Not Found with -> username or email : " + auth.getName());
		});
		return user;
	}

}
