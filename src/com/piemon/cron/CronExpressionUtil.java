package com.piemon.cron;

public class CronExpressionUtil {
	
	//秒、分、时、日期、月份、星期、年份
	public String cronExpressionFormat(String cronExpression) throws Exception{
		String[] subCrons = cronExpression.split(" ");
		int length = subCrons.length;
		if(length > 7 || length < 6){
			//标识cron有错误
			throw new Exception("cronExpress格式不正确");
		}
		String secondFormat = cronSecondFormat(subCrons[0]);
		String minuteFormat = cronMinuteFormat(subCrons[1]);
		String hourFormat = cronHourFormat(subCrons[2]);
		String dateFormat = cronDateFormat(subCrons[3]);
		String monthFormat = cronMonthFormat(subCrons[4]);
		String wekFormat = cronWeekFormat(subCrons[5]);
		String yearFormat = "";
		if(length == 7){
			yearFormat = cronYearFormat(subCrons[6]);
		}
		
		return yearFormat
			+ wekFormat + "\n"
			+ monthFormat + "\n"
			+ dateFormat + "\n" 
			+ hourFormat + "\n" 
			+ minuteFormat + "\n" 
			+ secondFormat + "\n" 
			+ "执行";
	}
	/**
	 * 秒的转换
	 * @throws Exception 
	 */
	public String cronSecondFormat(String cronSecondCode) throws Exception{
//		if(("*").equals(cronSecondCode)){
//			//标识每秒执行一次
//			return "每隔一秒";
//		}else if()
		if(validateIfStepBy(cronSecondCode)){
			//步进的
			String[] stepByStepCron = cronSecondCode.split("/");
			String stepByStepCronOne = stepByStepCron[0];
			String stepByStepCronTwo = stepByStepCron[1];
			if(validateIfPerSecond(stepByStepCronOne)){
				String template = "从第0秒开始，每隔 %s秒";
				String format = String.format(template, stepByStepCronTwo);
				return format;
			}else if(validateIfWithinSecond(stepByStepCronOne)){
				String[] cronSeconds = stepByStepCronOne.split("-");
				String template = "在第 %s到 %s秒内，每隔 %s秒";
				String format = String.format(template, cronSeconds[0],cronSeconds[1],stepByStepCronTwo);
				return format;
			}else{
				//纯数字的
				String template = "从第第 %s秒开始，每隔 %s秒";
				String format = String.format(template, stepByStepCronOne,stepByStepCronTwo);
				return format;
			}
			
		}else if(validateIfPerSecond(cronSecondCode)){
//			return "每一秒";
			return "";
			
		}else if(validateIfCustomerSecond(cronSecondCode)){
			String[] cronSeconds = cronSecondCode.split(",");
			String format = "第";
			String join = String.join("/", cronSeconds);
			format = format + join + "秒";
			return format;

		}else if(validateIfWithinSecond(cronSecondCode)){
			String[] cronSeconds = cronSecondCode.split("-");
			String template = "在第 %s到 %s秒内，每隔一秒";
			String format = String.format(template, cronSeconds[0],cronSeconds[1]);
			return format;
		}else{
			//不知道啥样式
			//纯数字的
			return cronSecondCode + "秒";
		}
		
	}
	
	
	public String cronMinuteFormat(String cronMinuteCode) throws Exception{
//		if(("*").equals(cronSecondCode)){
//			//标识每秒执行一次
//			return "每隔一秒";
//		}else if()
		if(validateIfStepBy(cronMinuteCode)){
			//步进的
			String[] stepByStepCron = cronMinuteCode.split("/");
			String stepByStepCronOne = stepByStepCron[0];
			String stepByStepCronTwo = stepByStepCron[1];
			if(validateIfPerMinute(stepByStepCronOne)){
				String template = "从第0分钟开始，每隔 %s分钟";
				String format = String.format(template, stepByStepCronTwo);
				return format;
			}else if(validateIfWithinMinute(stepByStepCronOne)){
				String[] cronSeconds = stepByStepCronOne.split("-");
				String template = "在第 %s到 %s分钟内，每隔 %s分钟";
				String format = String.format(template, cronSeconds[0],cronSeconds[1],stepByStepCronTwo);
				return format;
			}else{
				//纯数字的
				String template = "从第 %s分钟开始，每隔 %s分钟";
				String format = String.format(template, stepByStepCronOne,stepByStepCronTwo);
				return format;
			}
			
		}else if(validateIfPerSecond(cronMinuteCode)){
//			return "每隔一分钟";
			return "";
			
		}else if(validateIfCustomerSecond(cronMinuteCode)){
			String[] cronSeconds = cronMinuteCode.split(",");
			String format = "第";
			String join = String.join("/", cronSeconds);
			format = format + join + "分钟";
			return format;

		}else if(validateIfWithinSecond(cronMinuteCode)){
			String[] cronSeconds = cronMinuteCode.split("-");
			String template = "在第 %s到 %s分钟内每隔一分钟";
			String format = String.format(template, cronSeconds[0],cronSeconds[1]);
			return format;
		}else{
			//不知道啥样式
			//纯数字的
			return cronMinuteCode + "分";
		}
		
	}
	
	public String cronHourFormat(String cronHourCode) throws Exception{
//		if(("*").equals(cronSecondCode)){
//			//标识每秒执行一次
//			return "每隔一秒";
//		}else if()
		if(validateIfStepBy(cronHourCode)){
			//步进的
			String[] stepByStepCron = cronHourCode.split("/");
			String stepByStepCronOne = stepByStepCron[0];
			String stepByStepCronTwo = stepByStepCron[1];
			if(validateIfPerHour(stepByStepCronOne)){
				String template = "从第0小时开始，每隔 %s小时";
				String format = String.format(template, stepByStepCronTwo);
				return format;
			}else if(validateIfWithinMinute(stepByStepCronOne)){
				String[] cronHours = stepByStepCronOne.split("-");
				String template = "在第 %s到 %s小时内，每隔 %s小时";
				String format = String.format(template, cronHours[0],cronHours[1],stepByStepCronTwo);
				return format;
			}else{
				//纯数字的
				String template = "从第 %s小时开始，每隔 %s小时";
				String format = String.format(template, stepByStepCronOne,stepByStepCronTwo);
				return format;
			}
			
		}else if(validateIfPerHour(cronHourCode)){
//			return "每隔一小时";
			return "";
			
		}else if(validateIfCustomerHour(cronHourCode)){
			String[] cronHours = cronHourCode.split(",");
			String format = "第";
			String join = String.join("/", cronHours);
			format = format + join + "小时";
			return format;

		}else if(validateIfWithinHour(cronHourCode)){
			String[] cronHours = cronHourCode.split("-");
			String template = "在第 %s到 %s小时内每隔一小时";
			String format = String.format(template, cronHours[0],cronHours[1]);
			return format;
		}else{
			//不知道啥样式
			//纯数字的
			return cronHourCode + "点";
		}
		
	}
	
	
	
	
	
	public String cronDateFormat(String cronDateCode){
		if(validateIfStepBy(cronDateCode)){
			//步进的
			String[] stepByStepCron = cronDateCode.split("/");
			String stepByStepCronOne = stepByStepCron[0];
			String stepByStepCronTwo = stepByStepCron[1];
			if(validateIfPerDate(stepByStepCronOne)){
				String template = "从1号开始，每隔 %s日";
				String format = String.format(template, stepByStepCronTwo);
				return format;
			}else if(validateIfWithinDate(stepByStepCronOne)){
				String[] cronDates = stepByStepCronOne.split("-");
				String template = "在 %s号到 %s号内，每隔 %s天";
				String format = String.format(template, cronDates[0],cronDates[1],stepByStepCronTwo);
				return format;
			}else{
				//纯数字的
				String template = "从 %s号开始，每隔 %s天";
				String format = String.format(template, stepByStepCronOne,stepByStepCronTwo);
				return format;
			}
			
		}else if(validateIfPerDate(cronDateCode)){
//			return "每隔一天";
			return "";
			
		}else if(validateIfCustomerDate(cronDateCode)){
			String[] cronDates = cronDateCode.split(",");
			String format = "第";
			String join = String.join("/", cronDates);
			format = format + join + "号";
			return format;

		}else if(validateIfWithinDate(cronDateCode)){
			String[] cronDates = cronDateCode.split("-");
			String template = "在 %s号到 %s号内每隔一天";
			String format = String.format(template, cronDates[0],cronDates[1]);
			return format;
		}else if(cronDateCode.equals("?")){
			return "";
		}else if (cronDateCode.equals("L")){
			return "当月的最后一天";
		}else if(cronDateCode.equals("W")){
			return "当月离今天最近的工作日";
		}else if(cronDateCode.equals("LW")){
			return "当月的最后一个工作日";
		}else{
			//不知道啥样式
			//纯数字的
			return "第" + cronDateCode + "号";
		}
		
	}
	public String cronMonthFormat(String cronMonthCode){
		if(validateIfStepBy(cronMonthCode)){
			//步进的
			String[] stepByStepCron = cronMonthCode.split("/");
			String stepByStepCronOne = stepByStepCron[0];
			String stepByStepCronTwo = stepByStepCron[1];
			if(validateIfPerMonth(stepByStepCronOne)){
				String template = "从1月开始，每隔 %s个月";
				String format = String.format(template, stepByStepCronTwo);
				return format;
			}else if(validateIfWithinMonth(stepByStepCronOne)){
				String[] cronMonthes = stepByStepCronOne.split("-");
				String template = "在 %s月到 %s月内，每隔 %s个月";
				String format = String.format(template, cronMonthes[0],cronMonthes[1],stepByStepCronTwo);
				return format;
			}else{
				//纯数字的
				String template = "从 %s月开始，每隔 %s个月";
				String format = String.format(template, stepByStepCronOne,stepByStepCronTwo);
				return format;
			}
			
		}else if(validateIfPerMonth(cronMonthCode)){
//			return "每隔一个月";
			return "";
			
		}else if(validateIfCustomerMonth(cronMonthCode)){
			String[] cronMonthes = cronMonthCode.split(",");
			String format = "第";
			String join = String.join("/", cronMonthes);
			format = format + join + "月";
			return format;

		}else if(validateIfWithinMonth(cronMonthCode)){
			String[] cronMonthes = cronMonthCode.split("-");
			String template = "在 %s月到 %s月内每隔一个月";
			String format = String.format(template, cronMonthes[0],cronMonthes[1]);
			return format;
		}else{
			//不知道啥样式
			//纯数字的
			return cronMonthCode + "月";
		}
		
	}
	
	public String cronWeekFormat(String cronWeekCode){
		if(validateIfStepBy(cronWeekCode)){
			//步进的
			String[] stepByStepCron = cronWeekCode.split("/");
			String stepByStepCronOne = stepByStepCron[0];
			String stepByStepCronTwo = stepByStepCron[1];
			if(validateIfPerDate(stepByStepCronOne)){
				String template = "从星期1开始，每隔 %s天";
				String format = String.format(template, stepByStepCronTwo);
				return format;
			}else if(validateIfWithinDate(stepByStepCronOne)){
				String[] cronWeeks = stepByStepCronOne.split("-");
				String template = "在星期 %s到星期 %s内，每隔 %s天";
				String format = String.format(template, cronWeeks[0],cronWeeks[1],stepByStepCronTwo);
				return format;
			}else{
				//纯数字的
				String template = "从星期 %s开始，每隔 %s天";
				String format = String.format(template, stepByStepCronOne,stepByStepCronTwo);
				return format;
			}
			
		}else if(validateIfPerWeek(cronWeekCode)){
//			return "每个星期";
			return "";
			
		}else if(validateIfCustomerWeek(cronWeekCode)){
			String[] cronWeeks = cronWeekCode.split(",");
			String format = "星期";
			String join = String.join("/", cronWeeks);
			format = format + join;
			return format;

		}else if(validateIfWithinWeek(cronWeekCode)){
			String[] cronWeeks = cronWeekCode.split("-");
			String template = "在星期 %s到星期 %s内每隔一天";
			String format = String.format(template, cronWeeks[0],cronWeeks[1]);
			return format;
		}else if(cronWeekCode.equals("?")){
			return "";
		}else if (validateIfLOfWeek(cronWeekCode)){
			String[] LWeeks = cronWeekCode.split("L");
			int week = Integer.parseInt(LWeeks[0]);
			
			return "当月的最后一个星期" + (week - 1);
		}else if(validateIfSharpOfWeek(cronWeekCode)){
			String[] LWeeks = cronWeekCode.split("#");
			String LWeeksOne = LWeeks[0];
			String LWeeksTwo = LWeeks[1];
			String template = "当月第 %s个周的星期 %s";
			String format = String.format(template, LWeeksTwo,LWeeksOne);
			return format;
		}else{
			//不知道啥样式
			//纯数字的
			return "星期" + cronWeekCode;
		}
		
	}
	
	
	public String cronYearFormat(String cronYearCode){
		if(validateIfStepBy(cronYearCode)){
			//步进的
			String[] stepByStepCron = cronYearCode.split("/");
			String stepByStepCronOne = stepByStepCron[0];
			String stepByStepCronTwo = stepByStepCron[1];
			if(validateIfPerYear(stepByStepCronOne)){
				String template = "从1970年开始，每隔 %s年";
				String format = String.format(template, stepByStepCronTwo);
				return format;
			}else if(validateIfWithinYear(stepByStepCronOne)){
				String[] cronYears = stepByStepCronOne.split("-");
				String template = "在 %s年到 %s年内，每隔 %s年";
				String format = String.format(template, cronYears[0],cronYears[1],stepByStepCronTwo);
				return format;
			}else{
				//纯数字的
				String template = "从 %s年开始，每隔 %s年";
				String format = String.format(template, stepByStepCronOne,stepByStepCronTwo);
				return format;
			}
			
		}else if(validateIfPerYear(cronYearCode)){
//			return "每一秒";
			return "";
			
		}else if(validateIfCustomerYear(cronYearCode)){
			String[] cronSeconds = cronYearCode.split(",");
			String format = "在";
			String join = String.join("/", cronSeconds);
			format = format + join + "年";
			return format;

		}else if(validateIfWithinYear(cronYearCode)){
			String[] cronYears = cronYearCode.split("-");
			String template = "在 %s年到 %s年内的每一年";
			String format = String.format(template, cronYears[0],cronYears[1]);
			return format;
		}else{
			//不知道啥样式
			//纯数字的
			return cronYearCode + "年";
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	public boolean validateIfStepBy(String cron){
		if(cron.indexOf("/") != -1 
				&& cron.indexOf("/") != (cron.length() - 1)
				&& cron.split("/").length == 2
				&& cron.indexOf(",") == -1){
			return true;
		}
		return false;
	}
	public boolean validateIfPerSecond(String cron){
		return "*".equals(cron);
	}
	
	public boolean validateIfCustomerSecond(String cron){
		if(cron.indexOf(",") != -1 
				&& cron.indexOf(",") != (cron.length() - 1)){
			return true;
		}
		return false;
	}
	public boolean validateIfWithinSecond(String cron){
		if(cron.indexOf("-") != -1 
				&& cron.indexOf("-") != (cron.length() - 1)
				&& cron.split("-").length == 2){
			String[] crons = cron.split("-");
			int min = Integer.parseInt(crons[0]);
			int max = Integer.parseInt(crons[1]);
			if(min >= 0 && min <=60 && max >= 0 && max <= 60 && max > min){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	public boolean validateIfPerMinute(String cron){
		return "*".equals(cron);
	}
	
	public boolean validateIfCustomerMinute(String cron){
		if(cron.indexOf(",") != -1 
				&& cron.indexOf(",") != (cron.length() - 1)){
			String[] crons = cron.split(",");
			boolean flag = true;
			for (String cr : crons) {
				int minute = Integer.parseInt(cr);
				if(minute > 60 || minute < 0){
					flag = false;
				}
			}
			return flag;
		}
		return false;
	}
	public boolean validateIfWithinMinute(String cron){
		if(cron.indexOf("-") != -1 
				&& cron.indexOf("-") != (cron.length() - 1)
				&& cron.split("-").length == 2){
			String[] crons = cron.split("-");
			int min = Integer.parseInt(crons[0]);
			int max = Integer.parseInt(crons[1]);
			if(min >= 0 && min <=60 && max >= 0 && max <= 60 && max > min){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	public boolean validateIfPerHour(String cron){
		return "*".equals(cron);
	}
	
	public boolean validateIfCustomerHour(String cron){
		if(cron.indexOf(",") != -1 
				&& cron.indexOf(",") != (cron.length() - 1)){
			String[] crons = cron.split(",");
			boolean flag = true;
			for (String cr : crons) {
				int minute = Integer.parseInt(cr);
				if(minute > 24 || minute < 0){
					flag = false;
				}
			}
			return flag;
		}
		return false;
	}
	public boolean validateIfWithinHour(String cron){
		if(cron.indexOf("-") != -1 
				&& cron.indexOf("-") != (cron.length() - 1)
				&& cron.split("-").length == 2){
			String[] crons = cron.split("-");
			int min = Integer.parseInt(crons[0]);
			int max = Integer.parseInt(crons[1]);
			if(min >= 0 && min <= 24 && max >= 0 && max <= 24 && max > min){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	public boolean validateIfPerDate(String cron){
		return "*".equals(cron);
	}
	
	public boolean validateIfCustomerDate(String cron){
		if(cron.indexOf(",") != -1 
				&& cron.indexOf(",") != (cron.length() - 1)){
			String[] crons = cron.split(",");
			boolean flag = true;
			for (String cr : crons) {
				int minute = Integer.parseInt(cr);
				if(minute > 31 || minute < 0){
					flag = false;
				}
			}
			return flag;
		}
		return false;
	}
	public boolean validateIfWithinDate(String cron){
		if(cron.indexOf("-") != -1 
				&& cron.indexOf("-") != (cron.length() - 1)
				&& cron.split("-").length == 2){
			String[] crons = cron.split("-");
			int min = Integer.parseInt(crons[0]);
			int max = Integer.parseInt(crons[1]);
			if(min >= 1 && min <= 31 && max >= 1 && max <= 31 && max > min){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	public boolean validateIfPerMonth(String cron){
		return "*".equals(cron);
	}
	
	public boolean validateIfCustomerMonth(String cron){
		if(cron.indexOf(",") != -1 
				&& cron.indexOf(",") != (cron.length() - 1)){
			String[] crons = cron.split(",");
			boolean flag = true;
			for (String cr : crons) {
				int month = Integer.parseInt(cr);
				if(month > 12 || month < 1){
					flag = false;
				}
			}
			return flag;
		}
		return false;
	}
	public boolean validateIfWithinMonth(String cron){
		if(cron.indexOf("-") != -1 
				&& cron.indexOf("-") != (cron.length() - 1)
				&& cron.split("-").length == 2){
			String[] crons = cron.split("-");
			int min = Integer.parseInt(crons[0]);
			int max = Integer.parseInt(crons[1]);
			if(min >= 1 && min <= 12 && max >= 1 && max <= 12 && max > min){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	public boolean validateIfPerWeek(String cron){
		return "*".equals(cron);
	}
	
	public boolean validateIfCustomerWeek(String cron){
		if(cron.indexOf(",") != -1 
				&& cron.indexOf(",") != (cron.length() - 1)){
			String[] crons = cron.split(",");
			boolean flag = true;
			for (String cr : crons) {
				int month = Integer.parseInt(cr);
				if(month > 7 || month < 1){
					flag = false;
				}
			}
			return flag;
		}
		return false;
	}
	public boolean validateIfWithinWeek(String cron){
		if(cron.indexOf("-") != -1 
				&& cron.indexOf("-") != (cron.length() - 1)
				&& cron.split("-").length == 2){
			String[] crons = cron.split("-");
			int min = Integer.parseInt(crons[0]);
			int max = Integer.parseInt(crons[1]);
			if(min >= 1 && min <= 7 && max >= 1 && max <= 7 && max > min){
				return true;
			}
		}
		return false;
	}
	public boolean validateIfLOfWeek(String cron){
		if(cron.indexOf("L") == (cron.length() - 1)){
			String[] LWeeks = cron.split("L");
			int count = Integer.parseInt(LWeeks[0]);
			if(count >= 1 && count <= 7){
				return true;
			}
		}
		return false;
	}
	
	public boolean validateIfSharpOfWeek(String cron){
		if(cron.indexOf("#") != -1){
			String[] LWeeks = cron.split("#");
			int LWeeksOne = Integer.parseInt(LWeeks[0]);
			int LWeeksTwo = Integer.parseInt(LWeeks[1]);
			if(LWeeksOne >= 1 && LWeeksOne <= 7 && LWeeksTwo >= 1 && LWeeksTwo <= 6){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	
	public boolean validateIfPerYear(String cron){
		return "*".equals(cron);
	}
	
	public boolean validateIfCustomerYear(String cron){
		if(cron.indexOf(",") != -1 
				&& cron.indexOf(",") != (cron.length() - 1)){
			return true;
		}
		return false;
	}
	public boolean validateIfWithinYear(String cron){
		if(cron.indexOf("-") != -1 
				&& cron.indexOf("-") != (cron.length() - 1)
				&& cron.split("-").length == 2){
			String[] crons = cron.split("-");
			int min = Integer.parseInt(crons[0]);
			int max = Integer.parseInt(crons[1]);
//			if(min >= 0 && min <=60 && max >= 0 && max <= 60 && max > min){
				return true;
//			}
		}
		return false;
	}
	
	
	
	
	
	public static void main(String[] args) {
		CronExpressionUtil cronExpressionUtil = new CronExpressionUtil();
		try {
			String cronExpressionFormat = cronExpressionUtil.cronExpressionFormat("0 3/5 3,5,14 1-30 * ?");
			System.out.println(cronExpressionFormat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
