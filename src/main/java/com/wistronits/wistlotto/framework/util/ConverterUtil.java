package com.wistronits.wistlotto.framework.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

/**
 * 类型转换工具
 * 
 * @author 盛广立 2019年1月16日
 */
public class ConverterUtil {

	// 类型映射设置
	private static final ModelMapper modelMapper = new ModelMapper();

	// JSON转换设置
	private static final ObjectMapper objectMapper = new ObjectMapper();

	static {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		JavaTimeModule module = new JavaTimeModule();
		// JSON <-> LocalDate
		DateTimeFormatter dateFormatter = ConverterUtil.getFormatter(CommonConst.LOCAL_DATE_STYLE);
		module.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
		module.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));

		// JSON <-> LocalDateTime
		DateTimeFormatter dateTimeFormatter = ConverterUtil.getFormatter(CommonConst.LOCAL_DATE_TIME_STYLE);
		module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
		module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));

		// JSON <-> LocalTime
		DateTimeFormatter timeFormatter = ConverterUtil.getFormatter(CommonConst.LOCAL_TIME_STYLE);
		module.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
		module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));

		// JSON(String) <-> BigDecimal
		module.addSerializer(BigDecimal.class, new BigDecimalSerializer());
		module.addDeserializer(BigDecimal.class, new BigDecimalDeserializer());

		objectMapper.registerModule(module);

	}

	/**
	 * BigDecimal字符序列化
	 */
	public static class BigDecimalSerializer extends JsonSerializer<BigDecimal> {
		@Override
		public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {
			gen.writeString(value.toString());
		}
	}

	/**
	 * BigDecimal字符反序列化
	 */
	public static class BigDecimalDeserializer extends JsonDeserializer<BigDecimal> {
		@Override
		public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			String s = p.getValueAsString();
			return new BigDecimal(s);
		}
	}

	/**
	 * 对象转换
	 * 
	 * @param src 源对象
	 * @param des 目标对象
	 */
	public static void convertObject(Object src, Object des) {
		modelMapper.map(src, des);
	}

	/**
	 * 对象转换
	 * 
	 * @param src   源对象
	 * @param clazz 目标类型
	 * @return 目标对象
	 */
	public static <T> T convertObject(Object src, Class<T> clazz) {
		return modelMapper.map(src, clazz);
	}

	/**
	 * JSON字符串转换成对象
	 * 
	 * @param json  JSON字符串
	 * @param clazz 对象类型
	 * @return 对象
	 */
	public static <T> T convertFromJSONToApp(String json, Class<T> clazz) {
		T v = null;
		try {
			v = objectMapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return v;
	}

	/**
	 * 对象转换成JSON字符串
	 *
	 * @param object 对象
	 * @return JSON字符串
	 */
	public static String convertFromAppToJSON(Object object) {
		if (Objects.isNull(object)) {
			return "{}";
		}

		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "null";
		}
	}

	/**
	 * String转换成LocalDateTime
	 *
	 * @param str String
	 * @return LocalDateTime
	 */
	public static LocalDateTime convertFromStringToLocalDateTime(String str) {
		LocalDateTime result = null;
		if (StringUtils.isNotEmpty(str)) {
			result = LocalDateTime.parse(str, getFormatter(CommonConst.LOCAL_DATE_TIME_STYLE));
		}
		return result;
	}

	/**
	 * LocalDateTime转换成String
	 *
	 * @param datetime LocalDateTime
	 * @return String
	 */
	public static String convertFromLocalDateTimeToString(LocalDateTime datetime) {
		String result = null;
		if (Objects.nonNull(datetime)) {
			result = datetime.format(getFormatter(CommonConst.LOCAL_DATE_TIME_STYLE));
		}
		return result;
	}

	/**
	 * String转换成LocalDate
	 *
	 * @param str String
	 * @return LocalDate
	 */
	public static LocalDate convertFromStringToLocalDate(String str) {
		LocalDate result = null;
		if (StringUtils.isNotEmpty(str)) {
			result = LocalDate.parse(str, getFormatter(CommonConst.LOCAL_DATE_STYLE));
		}
		return result;
	}

	/**
	 * LocalDate转换成String
	 *
	 * @param date LocalDate
	 * @return String
	 */
	public static String convertFromLocalDateToString(LocalDate date) {
		String result = null;
		if (Objects.nonNull(date)) {
			result = date.format(getFormatter(CommonConst.LOCAL_DATE_STYLE));
		}
		return result;
	}

	/**
	 * String转换成LocalTime
	 *
	 * @param str String
	 * @return LocalTime
	 */
	public static LocalTime convertFromStringToLocalTime(String str) {
		LocalTime result = null;
		if (StringUtils.isNotEmpty(str)) {
			result = LocalTime.parse(str, getFormatter(CommonConst.LOCAL_TIME_STYLE));
		}
		return result;
	}

	/**
	 * LocalTime转换成String
	 *
	 * @param time LocalTime
	 * @return String
	 */
	public static String convertFromLocalTimeToString(LocalTime time) {
		String result = null;
		if (Objects.nonNull(time)) {
			result = time.format(getFormatter(CommonConst.LOCAL_TIME_STYLE));
		}
		return result;
	}

	/**
	 * LocalDateTime转换成String
	 *
	 * @param datetime LocalDateTime
	 * @return String（yyyy年MM月dd日）
	 */
	public static String convertFromLocalDateTimeToStringJp(LocalDateTime datetime) {
		String result = null;
		if (Objects.nonNull(datetime)) {
			result = datetime.format(getFormatter(CommonConst.LOCAL_DATE_CN_STYLE));
		}
		return result;
	}

	/**
	 * 取得日期格式化
	 *
	 * @param style 日期格式化风格
	 * @return 日期格式化
	 * @exception IllegalArgumentException 风格不正确
	 */
	public static DateTimeFormatter getFormatter(String style) {
		if (StringUtils.isBlank(style)) {
			throw new IllegalArgumentException("日期格式化风格不正确。");
		}
		return DateTimeFormatter.ofPattern(style).withResolverStyle(ResolverStyle.STRICT);
	}

}
