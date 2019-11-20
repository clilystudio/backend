package com.wistronits.wistlotto.framework.util;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.common.TextParsingException;
import com.univocity.parsers.common.processor.RowProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.wistronits.wistlotto.framework.exception.SystemException;
import com.wistronits.wistlotto.framework.message.MessageId;

/**
 * CSV文件处理工具类
 * 
 * @author 盛广立 2019年1月11日
 */
public class CsvUtil {

	/**
	 * CSV文件载入
	 * 
	 * @param mpf       CSV文件
	 * @param processor CSV处理器
	 * @throws SystemException 系统异常
	 */
	public static void loadFile(MultipartFile mpf, RowProcessor processor) throws SystemException {
		InputStream in = null;
		try {
			in = mpf.getInputStream();
			CsvParserSettings settings = new CsvParserSettings();
			settings.setHeaderExtractionEnabled(true);
			settings.getFormat().setLineSeparator("\n");
			settings.setNormalizeLineEndingsWithinQuotes(false);
			settings.setProcessor(processor);
			settings.setColumnReorderingEnabled(false);
			CsvParser parser = new CsvParser(settings);
			parser.parse(in, "UTF-8");
		} catch (TextParsingException e) {
			throw new SystemException(e, MessageId.MBE1002);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new SystemException(e, MessageId.MBE1003);
		} catch (IOException e) {
			throw new SystemException(e, MessageId.MBE1001);
		}
	}
}
