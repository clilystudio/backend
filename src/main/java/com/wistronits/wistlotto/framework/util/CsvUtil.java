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

public class CsvUtil {
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
