package com.asosyalbebe.samplespring.utils;

import java.text.MessageFormat;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class Logger {

	private static final String FATAL_PREFIX = "FATAL----> ";

	private static final Object[] NULL_ARRAY = new Object[] {};

	private final org.slf4j.Logger logger;

	private final Marker fatalMarker;

	private Logger(org.slf4j.Logger logger) {
		this.logger = logger;

		this.fatalMarker = MarkerFactory.getMarker("FATAL");
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param message the message
	 */
	public void debug(Object obj) {
		this.debug(null, obj.toString(), Logger.NULL_ARRAY);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param message the message
	 */
	public void debug(String message) {
		this.debug(null, message, Logger.NULL_ARRAY);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param message the format message
	 * @param params the params to the message
	 */
	public void debug(String message, Object... params) {
		this.debug(null, message, params);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param t the {@link Throwable} applicable to the log
	 * @param message the message
	 */
	public void debug(Throwable t, String message) {
		this.debug(t, message, Logger.NULL_ARRAY);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param t the {@link Throwable} applicable to the log
	 * @param message the format message
	 * @param params the params to the message
	 */
	public void debug(Throwable t, String message, Object... params) {
		if (this.logger.isDebugEnabled()) {
			if (t != null) {
				this.logger.debug(this.format(message, params), t);
			} else {
				this.logger.debug(this.format(message, params));
			}
		}
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param message the message
	 */
	public void error(String message) {
		this.error(null, message, Logger.NULL_ARRAY);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param message the format message
	 * @param params the params to the message
	 */
	public void error(String message, Object... params) {
		this.error(null, message, params);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param t the {@link Throwable} applicable to the log
	 * @param message the message
	 */
	public void error(Throwable t, String message) {
		this.error(t, message, Logger.NULL_ARRAY);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param t the {@link Throwable} applicable to the log
	 * @param message the format message
	 * @param params the params to the message
	 */
	public void error(Throwable t, String message, Object... params) {
		if (this.logger.isErrorEnabled()) {
			if (t != null) {
				this.logger.error(this.format(message, params), t);
			} else {
				this.logger.error(this.format(message, params));
			}
		}
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param message the message
	 */
	public void fatal(String message) {
		this.trace(null, message, Logger.NULL_ARRAY);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param message the format message
	 * @param params the params to the message
	 */
	public void fatal(String message, Object... params) {
		this.trace(null, message, params);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param t the {@link Throwable} applicable to the log
	 * @param message the message
	 */
	public void fatal(Throwable t, String message) {
		this.fatal(t, message, Logger.NULL_ARRAY);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param t the {@link Throwable} applicable to the log
	 * @param message the format message
	 * @param params the params to the message
	 */
	public void fatal(Throwable t, String message, Object... params) {
		if (t != null) {
			this.logger.error(this.fatalMarker, Logger.FATAL_PREFIX + this.format(message, params), t);
		} else {
			this.logger.error(this.fatalMarker, Logger.FATAL_PREFIX + this.format(message, params));
		}
	}

	private String format(String message, Object... params) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(message);

		if ((params == null) || (params.length == 0)) {
			return strBuild.toString();
		}

		return MessageFormat.format(strBuild.toString(), params);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param message the message
	 */
	public void info(String message) {
		this.info(null, message, Logger.NULL_ARRAY);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param message the format message
	 * @param params the params to the message
	 */
	public void info(String message, Object... params) {
		this.info(null, message, params);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param t the {@link Throwable} applicable to the log
	 * @param message the message
	 */
	public void info(Throwable t, String message) {
		this.info(t, message, Logger.NULL_ARRAY);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param t the {@link Throwable} applicable to the log
	 * @param message the format message
	 * @param params the params to the message
	 */
	public void info(Throwable t, String message, Object... params) {
		if (this.logger.isInfoEnabled()) {
			if (t != null) {
				this.logger.info(this.format(message, params), t);
			} else {
				this.logger.info(this.format(message, params));
			}
		}
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param message the message
	 */
	public void trace(String message) {
		this.trace(null, message, Logger.NULL_ARRAY);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param message the format message
	 * @param params the params to the message
	 */
	public void trace(String message, Object... params) {
		this.trace(null, message, params);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param t the {@link Throwable} applicable to the log
	 * @param message the message
	 */
	public void trace(Throwable t, String message) {
		this.trace(t, message, Logger.NULL_ARRAY);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param t the {@link Throwable} applicable to the log
	 * @param message the format message
	 * @param params the params to the message
	 */
	public void trace(Throwable t, String message, Object... params) {
		if (this.logger.isTraceEnabled()) {
			if (t != null) {
				this.logger.trace(this.format(message, params), t);
			} else {
				this.logger.trace(this.format(message, params));
			}
		}
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param message the message
	 */
	public void warn(String message) {
		this.warn(null, message, Logger.NULL_ARRAY);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param message the format message
	 * @param params the params to the message
	 */
	public void warn(String message, Object... params) {
		this.warn(null, message, params);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param t the {@link Throwable} applicable to the log
	 * @param message the message
	 */
	public void warn(Throwable t, String message) {
		this.warn(t, message, Logger.NULL_ARRAY);
	}

	/**
	 * Convenience method to log a message
	 * 
	 * @param t the {@link Throwable} applicable to the log
	 * @param message the format message
	 * @param params the params to the message
	 */
	public void warn(Throwable t, String message, Object... params) {
		if (this.logger.isWarnEnabled()) {
			if (t != null) {
				this.logger.warn(this.format(message, params), t);
			} else {
				this.logger.warn(this.format(message, params));
			}
		}
	}

	/**
	 * Returns an instance of Logger for the class
	 * 
	 * @param clazz the clazz of the log
	 * @return the logger - {@link SPLogger}
	 */
	public static final Logger getLogger(Class<?> clazz) {
		return new Logger(LoggerFactory.getLogger(clazz));
	}

	/**
	 * Returns an instance of Logger with the class
	 * 
	 * @param name the name of the log
	 * @return the logger - {@link SPLogger}
	 */
	public static final Logger getLogger(String name) {
		return new Logger(LoggerFactory.getLogger(name));
	}

}
