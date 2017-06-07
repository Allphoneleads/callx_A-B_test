package com.allphoneleads.api.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.allphoneleads.api.proctor.RoutingSpecification;

public class AplUtils {
	private static final Logger logger = LoggerFactory.getLogger(AplUtils.class);
	public static final String PROMO_ID_DESCRIPTION = "Please enter your promotion ID";
	public static String IVR_SPEAK_VOICE_WOMAN = "WOMAN";
	public static String IVR_SPEAK_LANG_EN_US = "en-US";
	public static String IVR_SPEAK_TEXT_GOODBYE = "Goodbye!";
	public static String IVR_HANGUP_TEXT_BUSY = "Busy";
	public static String IVR_HTTPMETHOD_POST = "POST";
	public static String IVR_HTTPMETHOD_GET = "GET";
	public static String IVR_SERVICE_PATH_CALLBACK = "/ivr/callback/";
	public static String IVR_SERVICE_PATH_CALL_DIGIT = "/ivr/digit/";
	public static String IVR_SERVICE_PATH_CALL_DIGIT_TEST = "/ivr/digit/test/";
	public static String IVR_SERVICE_PATH_CALL_DIGIT_TEST_PROMPT = "/ivr/digit/testprompt/";
	public static String IVR_SERVICE_PATH_CALL_RECORDCALL = "/ivr/recordedcall/";
	public static String IVR_SERVICE_PATH_CALL_START = "/ivr/callstart/";
	public static String IVR_SERVICE_PATH_CALL_ZIP_VERIFY = "/ivr/zip/";
	public static String IVR_SERVICE_PATH_CALL_ZIP_VERIFY_TEST = "/ivr/zip/test/";
	public static String IVR_SERVICE_PATH_CALL_ZIP_VERIFY_TEST_PROMPT = "/ivr/zip/testprompt/";
	public static String IVR_SERVICE_ZIP_VERIFY = "Please enter your five digit zip code";
	public static String AUTO_CAMPAIGN_CATEGORY = "Auto Insurance";
	public static String TEST_CALL_HANG_UP_MESSAGE = "test call hangs up,Bye.";
	public static String IVR_SERVICE_PATH_CALL_OUT_DIGIT = "/ivr/out/digit/";
	public static String IVR_SERVICE_PATH_CALL_OUT_ZIP_VERIFY = "/ivr/out/zip/";
	
	public static String TEXT = "Text";
	public static String MESSAGEUUID = "MessageUUID";
	public static String EOI_HTTPREQ_PATH = "phone";

	public static String CHAR_EQA = "=";
	public static String CHAR_PLUS = "+";
	public static String STRING_URL = "url";
	public static String STRING_PROMPT_TEXT_BLOCKED = "We're sorry. This service is not available at this moment. Please try later";
	public static String STRING_PROMPT_TEXT = "We're sorry. This service is not available in your location at this moment. Please try later";
	public static String STRING_PROMPT_TEXT_Test = "We're sorry. No offers available we can't forward your call ";
	public static String STRING_PROMPT_TEXT_NOOFFERS = "We're sorry. No offers available we can't forward your call ";
	public static String STRING_PROMPT_TEXT_PPROMONUMBER_ERROR = "We're sorry. The number you have called is not actived yet or not available this time . Please try later";
	public static String STRING_HANGUP_REASON_TEXT_INVALID_NUMBER = "We're sorry, You have used an invalid number. Thank you";
	public static String STRING_HANGUP_REASON_TEXT_INVALID_CAMPAIGN = "We're sorry, No sevices available from the campaign this time. Please try later. Thank you";
	public static String STRING_WITHOUT_PROMT = "withAudioPrompt";
	public static String STRING_ACTIVE = "active";
	public static String STRING_HANGUP_REASON_BLOCKED_NUMBER = "We're sorry, Currently this number has been blocked in our system. Thank you";
	public static String STRING_FINAL_PROMPT_TEXT = "Thank you.";

	public static String FROM = "From";
	public static String TO = "To";
	public static String DIRECTOIN = "Direction";
	public static String OUTBOUND = "outbound";
	public static String CALLUUID = "CallUUID";
	public static String BILL_RATE = "BillRate";
	public static String CALL_STATUS = "CallStatus";
	public static String A_LEG_UUID ="ALegUUID";
	public static String A_LEG_REQYEST_UUID ="ALegRequestUUID";

	public static String TEMPLATE_KEY = "key";
	public static String LOGIN_URL = "login_url";
	public static String EMAIL_TO = "to";
	public static String USER_PASSWORD = "user_password";
	public static String CAMPAIGN_NAME = "campaign_name";
	public static String CAMPAIGN_STATUS = "campaign_status";
	public static String EXPIRATION_DATE = "expiration_date";
	public static String FULL_NAME = "full_name";
	public static String OFFER_NAME = "offer_name";
	public static String OFFER_CHANGES = "offer_changes";
	public static String USER_EMAIL = "user_email";
	public static String VIEW_URL = "view_url";
	public static String CAMPAIGN_CHANGES = "campaign_changes";

	public static String TEMPLATE_HAS_HEADER = "hasheader";
	public static String TEMPLATE_HAS_FOOTER = "hasfooter";

	public static String BLOCKED_NUMBER = "BlockedNumber";
	public static String WHITELIST_NUMBER = "WhiteListedNumber";
	public static String CHANG_HISTORY_LOGIN = "Login";

	public static String DAYPART_1 = "06 AM - 10 AM";
	public static String DAYPART_2 = "10 AM - 02 PM";
	public static String DAYPART_3 = "02 PM - 06 PM";
	public static String DAYPART_4 = "06 PM - 10 PM";
	public static String DAYPART_5 = "10 PM - 02 AM";
	public static String DAYPART_6 = "02 AM - 06 AM";

	public static String PST_DAYPART_1 = "10 PM - 02 AM";
	public static String PST_DAYPART_2 = "02 AM - 06 AM";
	public static String PST_DAYPART_3 = "06 AM - 10 AM";
	public static String PST_DAYPART_4 = "10 AM - 02 PM";
	public static String PST_DAYPART_5 = "02 PM - 06 PM";
	public static String PST_DAYPART_6 = "06 PM - 10 PM";

	public static String HOUR_DAYPART_1 = "12 AM - 01 AM";
	public static String HOUR_DAYPART_2 = "01 AM - 02 AM";
	public static String HOUR_DAYPART_3 = "02 AM - 03 AM";
	public static String HOUR_DAYPART_4 = "03 AM - 04 AM";
	public static String HOUR_DAYPART_5 = "04 AM - 05 AM";
	public static String HOUR_DAYPART_6 = "05 AM - 06 AM";
	public static String HOUR_DAYPART_7 = "06 AM - 07 AM";
	public static String HOUR_DAYPART_8 = "07 AM - 08 AM";
	public static String HOUR_DAYPART_9 = "08 AM - 09 AM";
	public static String HOUR_DAYPART_10 = "09 AM - 10 AM";
	public static String HOUR_DAYPART_11 = "10 AM - 11 AM";
	public static String HOUR_DAYPART_12 = "11 AM - 12 PM";
	public static String HOUR_DAYPART_13 = "12 PM - 01 PM";
	public static String HOUR_DAYPART_14 = "01 PM - 02 PM";
	public static String HOUR_DAYPART_15 = "02 PM - 03 PM";
	public static String HOUR_DAYPART_16 = "03 PM - 04 PM";
	public static String HOUR_DAYPART_17 = "04 PM - 05 PM";
	public static String HOUR_DAYPART_18 = "05 PM - 06 PM";
	public static String HOUR_DAYPART_19 = "06 PM - 07 PM";
	public static String HOUR_DAYPART_20 = "07 PM - 08 PM";
	public static String HOUR_DAYPART_21 = "08 PM - 09 PM";
	public static String HOUR_DAYPART_22 = "09 PM - 10 PM";
	public static String HOUR_DAYPART_23 = "10 PM - 11 PM";
	public static String HOUR_DAYPART_24 = "11 PM - 12 AM";

	public static String ACTION_ADD = "Add";
	public static String ACTION_MODIFY = "Modify";
	public static String ACTION_DELETE = "Delete";

	public static String TOTAL_DURATION = "totalduration";
	public static String CONNECT_DURATION = "connectduration";
	
	public static String CACHE_ALL_IVR_KEYS = "All:IVRKeys";
	public static String CACHE_ALL_OFFERS_KEY = "All:Offers";
	public static String CACHE_ALL_USERS_KEY = "All:Users";
	public static String CACHE_ALL_CAMPAIGNS_KEY = "All:Campaigns";
	public static String CACHE_ALL_PROMONUMBERS_KEY = "All:Promos";
	public static String CACHE_ALL_IVRTREES_KEY = "All:IvrTrees";
	public static String CACHE_ALL_CAMPAIGNIVRFILTERS_KEY = "All:Campaign-IvrFilters";
	public static String CACHE_ALL_CALLRECORDS_KEY = "All:Records";
	public static String CACHE_ALL_OFFERS_WITH_DETAILS_KEY = "All:OffersDataWithMTD";
	public static String CACHE_ALL_USERTYPE_PUB_WITH_DETAILS_KEY = "All:PublisherDataWithMTD";
	public static String CACHE_ALL_USERTYPE_ADV_WITH_DETAILS_KEY = "All:AdvertiserDataWithMTD";
	public static String CACHE_ALL_CAMPAIGN_DETAILS_KEY = "All:CampaignsDataWithMTD";
	public static String CACHE_ALL_ZIP_CODES = "All:Zipcodes";
	public static String CACHE_ALL_PROMONUMBERS_ADMIN = "All:PromoDataWithMTD";
	public static String CACHE_OFFER_INPROGRESS_COUNT = "Offer:Inprogress-count";
	public static String CACHE_OFFER_BUDGET_COUNT = "Offer:Budget-count";
	public static String CACHE_ALL_ACTIVE_PROMONUMBERS_COUNTS = "All:Active-promonumbers-counts";
	public static String CACHE_ALL_CAMPAIGNS_SUMMARY = "All:CampaignSummary";
	
	public static String CACHE_ALL_BLOCKED_NUMBERS = "All:BLOCKED-NUMBERS";
	public static String CACHE_ALL_WHITE_LIST_NUMBERS = "All:WHITE-NUMBERS";

	public static String ALL_PUBS = "all_publishers";
	public static String ATLEAST_ONE_CAMP_PER_PUB = "atleast_one_campaign_per_publisher";
	public static String INVITE_ONLY = "invite_only";
	public static String NOTIFICATION_PUBLISHER_INVITE = "invite_publisher";
	public static String NOTIFICATION_APPLY_CAMPAIGNS = "apply_campaigns";
	public static String NOTIFICATION_APPROVED_CAMPAIGNS = "approved_campaigns";
	public static String NOTIFICATION_APPLY_LEADS = "apply_leads";
	public static String NOTIFICATION_APPROVED_LEADS = "approved_leads";
	
	public static String PROMONUMBERREASSIGN = "re-assign";
	
	public static String DRIPSINSERTION = "insert";
	public static String DRIPSCLOSELEAD = "close";
	public static String DRIPSLEADASCONVERT = "convert";
	public static String DRIPSDNC = "dnc";
	
	public static String LEAD_STATUS_ACTIVE = "Active";
	public static String LEAD_STATUS_NOT_ACTIVE = "Not Active";
	
	
	


	public static Date getNonExpiryDate() throws ParseException {

		String nonExpiryDate = "1969-12-31";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		return sdf.parse(nonExpiryDate);
	}

	public static String getDayRange(int i) {
		i = i + 1;
		if (i == 1)
			return AplUtils.HOUR_DAYPART_1;
		if (i == 2)
			return AplUtils.HOUR_DAYPART_2;
		if (i == 3)
			return AplUtils.HOUR_DAYPART_3;
		if (i == 4)
			return AplUtils.HOUR_DAYPART_4;
		if (i == 5)
			return AplUtils.HOUR_DAYPART_5;
		if (i == 6)
			return AplUtils.HOUR_DAYPART_6;
		if (i == 7)
			return AplUtils.HOUR_DAYPART_7;
		if (i == 8)
			return AplUtils.HOUR_DAYPART_8;
		if (i == 9)
			return AplUtils.HOUR_DAYPART_9;
		if (i == 10)
			return AplUtils.HOUR_DAYPART_10;
		if (i == 11)
			return AplUtils.HOUR_DAYPART_11;
		if (i == 12)
			return AplUtils.HOUR_DAYPART_12;
		if (i == 13)
			return AplUtils.HOUR_DAYPART_13;
		if (i == 14)
			return AplUtils.HOUR_DAYPART_14;
		if (i == 15)
			return AplUtils.HOUR_DAYPART_15;
		if (i == 16)
			return AplUtils.HOUR_DAYPART_16;
		if (i == 17)
			return AplUtils.HOUR_DAYPART_17;
		if (i == 18)
			return AplUtils.HOUR_DAYPART_18;
		if (i == 19)
			return AplUtils.HOUR_DAYPART_19;
		if (i == 20)
			return AplUtils.HOUR_DAYPART_20;
		if (i == 21)
			return AplUtils.HOUR_DAYPART_21;
		if (i == 22)
			return AplUtils.HOUR_DAYPART_22;
		if (i == 23)
			return AplUtils.HOUR_DAYPART_23;
		if (i == 24)
			return AplUtils.HOUR_DAYPART_24;

		return "None";
	}

	public static String getOsInfo(String information) {
		String os = null;
		if (information.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		} else if (information.toLowerCase().indexOf("mac") >= 0) {
			os = "Mac";
		} else if (information.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		} else if (information.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (information.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		} else {
			os = "UnKnown";
		}
		return os;
	}

	public static String getBrowserInfo(String Information) {
		String browsername = "";
		String browserversion = "";
		String browser = Information;
		if (browser.contains("MSIE")) {
			String subsString = browser.substring(browser.indexOf("MSIE"));
			String info[] = (subsString.split(";")[0]).split(" ");
			browsername = info[0];
			browserversion = info[1];
		} else if (browser.contains("Firefox")) {

			String subsString = browser.substring(browser.indexOf("Firefox"));
			String info[] = (subsString.split(" ")[0]).split("/");
			browsername = info[0];
			browserversion = info[1];
		} else if (browser.contains("Chrome")) {

			String subsString = browser.substring(browser.indexOf("Chrome"));
			String info[] = (subsString.split(" ")[0]).split("/");
			browsername = info[0];
			browserversion = info[1];
		} else if (browser.contains("Opera")) {

			String subsString = browser.substring(browser.indexOf("Opera"));
			String info[] = (subsString.split(" ")[0]).split("/");
			browsername = info[0];
			browserversion = info[1];
		} else if (browser.contains("Safari")) {

			String subsString = browser.substring(browser.indexOf("Safari"));
			String info[] = (subsString.split(" ")[0]).split("/");
			browsername = info[0];
			browserversion = info[1];
		}
		return browsername;
	}

	public static String getDayName(String day) {
		if (day == null)
			return null;

		switch (day) {
		case "mon":
			return "Monday";
		case "tue":
			return "Tuesday";
		case "wed":
			return "Wednesday";
		case "thu":
			return "Thursday";
		case "fri":
			return "Friday";
		case "sat":
			return "Saturday";
		case "sun":
			return "Sunday";
		case "all":
			return "All";

		default:
			throw new IllegalArgumentException("Unknown" + day);
		}
	}
	
	public static int getKeyValue(String key) {
		if (key == null)
			return 0;
//ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, ZERO, HASH, STAR, DEFAULT,NUll
		switch (key) {
		case "ONE":
			return 1;
		case "TWO":
			return 2;
		case "THREE":
			return 3;
		case "FOUR":
			return 4;
		case "FIVE":
			return 5;
		case "SIX":
			return 6;
		case "SEVEN":
			return 7;
		case "EIGHT":
			return 8;
		case "NINE":
			return 9;
		case "ZERO":
			return 0;

		default:
			throw new IllegalArgumentException("Unknown" + key);
		}
	}
	
	public static String getTimeZone(String zoneName) {
		if (zoneName == null)
			return null;

		switch (zoneName) {
		case "(UTC-04:00) Atlantic Standard Time":
			return "Etc/GMT+4";
		case "(UTC-05:00) Eastern Standard Time":
			return "Etc/GMT+5";
		case "(UTC-06:00) Central Standard Time":
			return "Etc/GMT+6";
		case "(UTC-07:00) Mountain Standard Time":
			return "Etc/GMT+7";
		case "(UTC-08:00) Pacific Standard Time":
			return "Etc/GMT+8";
		case "(UTC-09:00) Alaska Standard Time":
			return "Etc/GMT+9";
		case "(UTC+05:30) Indian Standard Time":
			return "Asia/Kolkata";
		case "(UTC+00:00) Greenwich Mean Time":
			return "Etc/GMT";
		case "(UTC+08:00) Singapore Standard Time":
			return "Etc/GMT-8";
		case "(UTC+09:00) Japan Standard Time":
			return "Etc/GMT-9";
		case "(UTC-03:30) Newfoundland Time Zone":
			return "America/St_Johns";

		default:
			throw new IllegalArgumentException("Unknown" + zoneName);
		}
	}

	public static String removeSpecialChar(String objectVlaue) {
		String value = objectVlaue.toLowerCase();
		value = value.replaceAll("[^a-zA-Z0-9]", "");
		return value;
	}
	
	public static int getMehtodValueFromSpecicificaiton(String campaignName, Method[] methods,RoutingSpecification groups) {
		int resultValue = -2;
		
		for (Method method : methods) {
			
			if(method.getName().equalsIgnoreCase("get"+campaignName+"value") && method.getGenericParameterTypes().length == 0){
				logger.debug("Method Name ="+method.getName() );
				
				Object typeValue = null;
				try {
					typeValue = method.invoke(groups);
					 resultValue = (int) typeValue ;

				} catch (IllegalAccessException e) {
					logger.error(" Something wrong with Reflection "+method.getName()+" --- "+e.getMessage() );
				} catch (IllegalArgumentException e) {
					logger.error(" Something wrong with Reflection "+method.getName()+" --- "+e.getMessage() );
				} catch (InvocationTargetException e) {
					logger.error(" Something wrong with Reflection "+method.getName()+" --- "+e.getMessage() );
				}
				break;
			}
		}
		logger.debug("Method Value = "+ resultValue);
		return resultValue;
	}
	
	public static int getObjectValueFromSpecicificaiton(String campaignName, final RoutingSpecification groups) {
		int resultValue = 0;
		Method[] methods = RoutingSpecification.class.getMethods();
		for (Method method : methods) {
			//getOldautoinsuranceoffersValue
			if(method.getName().equalsIgnoreCase("get"+campaignName+"value") && method.getGenericParameterTypes().length == 0){
				logger.debug("Method Name ="+method.getName() );
				
				Object typeValue = null;
				try {
					typeValue = method.invoke(groups);
					 resultValue = (int) typeValue ;

				} catch (IllegalAccessException e) {
					logger.error(" Something wrong with Reflection "+method.getName()+" --- "+e.getMessage() );
				} catch (IllegalArgumentException e) {
					logger.error(" Something wrong with Reflection "+method.getName()+" --- "+e.getMessage() );
				} catch (InvocationTargetException e) {
					logger.error(" Something wrong with Reflection "+method.getName()+" --- "+e.getMessage() );
				}
				break;
			}
		}
		logger.debug("Method Value = "+ resultValue);
		return resultValue;
	}
	
	// Method to get all days names for LUIS
	public static List<String> getDaysListForLUIS(){
	    List<String> daysList = new ArrayList<String>();
		
		daysList.add("Monday");
		daysList.add("Tuesday");
		daysList.add("Wednesday");
		daysList.add("Thursday");
		daysList.add("Friday");
		daysList.add("Saturday");
		daysList.add("Sunday");
		return daysList;
		
	}
	
	// Method to get weekday fullname
		public static String getWeekDayName(String dayName){
			
			Map<String, String> weekDaysMap = new HashMap<String, String>();
			
			weekDaysMap.put("Mon", "Monday");
			weekDaysMap.put("Tue", "Tuesday");
			weekDaysMap.put("Wed", "Wednesday");
			weekDaysMap.put("Thu", "Thursday");
			weekDaysMap.put("Fri", "Friday");
			weekDaysMap.put("Sat", "Saturday");
			weekDaysMap.put("Sun", "Sunday");
			
			return weekDaysMap.get(dayName);
		}
		
	// Method to get day value in number
	public static int getDayAsNumber(String dayName){
		
		Map<String, Integer> daysMap = new HashMap<String, Integer>();
		
		daysMap.put("SUNDAY", 1);
		daysMap.put("MONDAY", 2);
		daysMap.put("TUESDAY", 3);
		daysMap.put("WEDNESDAY", 4);
		daysMap.put("THURSDAY", 5);
		daysMap.put("FRIDAY", 6);
		daysMap.put("SATURDAY", 7);
		
		return daysMap.get(dayName);
	}
	
	
	// Method to get all numbers for LUIS
	public static List<String> getNumbersListForLUIS(){
		
		 List<String> numbersList = new ArrayList<String>();
		 
		 numbersList.add("One");
		 numbersList.add("Two");
		 numbersList.add("Three");
		 numbersList.add("Four");
		 numbersList.add("Five");
		 numbersList.add("Six");
		 numbersList.add("Seven");
		 numbersList.add("Eight");
		 numbersList.add("Nine");
		 numbersList.add("Ten");
		 numbersList.add("Eleven");
		 numbersList.add("Twelve");
		 numbersList.add("Thirteen");
		 numbersList.add("Fourteen");
		 numbersList.add("Fifteen");
		 numbersList.add("Sixteen");
		 numbersList.add("Seventeen");
		 numbersList.add("Eighteen");
		 numbersList.add("Ninteen");
		 numbersList.add("Twenty");
		 numbersList.add("Twenty One");
		 numbersList.add("Twenty Two");
		 numbersList.add("Twenty Three");
		 numbersList.add("Twenty Four");
		 
		 numbersList.add("1");numbersList.add("2");numbersList.add("3");numbersList.add("4");numbersList.add("5");numbersList.add("6");numbersList.add("7");
		 numbersList.add("8");numbersList.add("9");numbersList.add("10");numbersList.add("11");numbersList.add("12");numbersList.add("13");numbersList.add("14");
		 numbersList.add("15");numbersList.add("16");numbersList.add("17");numbersList.add("18");numbersList.add("19");numbersList.add("20");numbersList.add("21");
		 numbersList.add("22");numbersList.add("23");numbersList.add("24");
		 
		 return numbersList;
		
	}
	
	public static int getTimeAsNumber(String time){
		
		Map<String, Integer> timeMap = new HashMap<String, Integer>();
		timeMap.put("ONE", 1); timeMap.put("1", 1);
		timeMap.put("TWO", 2); timeMap.put("2", 2);
		timeMap.put("THREE", 3); timeMap.put("4", 3);
		timeMap.put("FOUR", 4); timeMap.put("4", 4);
		timeMap.put("FIVE", 5); timeMap.put("5", 5);
		timeMap.put("SIX", 6); timeMap.put("6", 6);
		timeMap.put("SEVEN", 7); timeMap.put("7", 7);
		timeMap.put("EIGHT", 8); timeMap.put("8", 8);
		timeMap.put("NINE", 9); timeMap.put("9", 9);
		timeMap.put("TEN", 10); timeMap.put("10", 10);
		timeMap.put("ELEVEN", 11); timeMap.put("11", 11);
		timeMap.put("TWELVE", 12); timeMap.put("12", 12);
		
		timeMap.put("THIRTEEN", 13); timeMap.put("13", 13);
		timeMap.put("FOURTEEN", 14); timeMap.put("14", 14);
		timeMap.put("FIFTEEN", 15); timeMap.put("15", 15);
		timeMap.put("SIXTEEN", 16); timeMap.put("16", 16);
		timeMap.put("SEVENTEEN", 17); timeMap.put("17", 17);
		timeMap.put("EIGHTEEN", 18); timeMap.put("18", 18);
		timeMap.put("NINTEEN", 19); timeMap.put("19", 19);
		timeMap.put("TWENTY", 20); timeMap.put("20", 20);
		timeMap.put("TWENTY ONE", 21); timeMap.put("21", 21);
		timeMap.put("TWENTY TWO", 22); timeMap.put("22", 22);
		timeMap.put("TWENTY THREE", 23); timeMap.put("23", 23);
		timeMap.put("TWEWNTY FOUR", 24); timeMap.put("24", 24);
		
		return timeMap.get(time);
	
		
	}
	
	
	public static String SMS = "sms";
	public static String Dial = "dial";
	public static String ScheduledSMS = "ScheduledSMS";
	public static String ScheduledCall = "ScheduledCall";
	public static String interruptSMS = "interruptSMS";
	public static String interruptDial = "interruptDial";
	public static String leadSchedulerActiveStatus = "active";
	public static String leadSchedulerSuspendedStatus = "suspended";
	public static String leadSchedulerDoneStatus = "done";
	
	
	
}
