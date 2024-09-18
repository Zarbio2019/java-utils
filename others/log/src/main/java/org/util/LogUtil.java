package org.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil
{
   //protected static Logger logger = LogManager.getLogger(getClass());
   static Logger logger = LogManager.getLogger(LogUtil.class);
   
   public static void main(String[] args)
   {
	   logger.info("INFO log");
	   logger.error("ERROR log");
	   logger.warn("WARN log");
	   logger.fatal("FATAL log");
	   logger.debug("DEBUG log");
   }
}