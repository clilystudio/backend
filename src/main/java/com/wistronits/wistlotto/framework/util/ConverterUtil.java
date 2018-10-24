package com.wistronits.wistlotto.framework.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Objects;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterUtil {

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
//        modelMapper.addConverter(new StringToLocalDateConverter());
//        modelMapper.addConverter(new StringToLocalDateTimeConverter());
//        modelMapper.addConverter(new StringToLocalTimeConverter());
//        modelMapper.addConverter(new LocalDateToStringConverter());
//        modelMapper.addConverter(new LocalDateTimeToStringConverter());
//        modelMapper.addConverter(new LocalTimeToStringConverter());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private static ObjectMapper objectMapper;

    @Inject
    public void setObjectMapper(ObjectMapper objectMapper) {
        ConverterUtil.objectMapper = objectMapper;
    }

    public static void convertObject(Object src, Object des) {
        modelMapper.map(src, des);
    }

    public static <T> T convertObject(Object src, Class<T> clazz) {
        return modelMapper.map(src, clazz);
    }

    public static <T> T convertFromJSONToApp(String json, Class<T> clazz) throws IOException {
        T v = null;
        try {
            v = objectMapper.readValue(json, clazz);
        } catch (JsonParseException e) {
            throw e;
        } catch (JsonMappingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
        return v;
    }

    /**
     * オブジェクトからJSONストリングに変換する。
     *
     * @param object オブジェクト
     * @return JSONストリング
     * @throws JsonProcessingException 
     */
    public static String convertFromAppToJSON(Object object) throws JsonProcessingException {
        if (Objects.isNull(object)) {
            return "{}";
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw e;
        }
    }

    /**
     * String から LocalDateTime に変換する。
     *
     * @param k String
     * @return LocalDateTime。ただし null や空文字が与えられたら null を返す。
     */
    public static LocalDateTime convertFromStringToLocalDateTime(String k) {
        LocalDateTime result = null;
        if (StringUtils.isNotEmpty(k)) {
            result = LocalDateTime.parse(k, getFormatter(CommonConst.LOCAL_DATE_TIME_STYLE));
        }
        return result;
    }

    /**
     * LocalDateTime から String に変換する。
     *
     * @param datetime 日時
     * @return String。ただし null が与えられたら null を返す。
     */
    public static String convertFromLocalDateTimeToString(LocalDateTime datetime) {
        String result = null;
        if (Objects.nonNull(datetime)) {
            result = datetime.format(getFormatter(CommonConst.LOCAL_DATE_TIME_STYLE));
        }
        return result;
    }

    /**
     * String から LocalDate に変換する。
     *
     * @param k String
     * @return LocalDate。ただし null や空文字が与えられたら null を返す。
     */
    public static LocalDate convertFromStringToLocalDate(String k) {
        LocalDate result = null;
        if (StringUtils.isNotEmpty(k)) {
            result = LocalDate.parse(k, getFormatter(CommonConst.LOCAL_DATE_STYLE));
        }
        return result;
    }

    /**
     * LocalDate から String に変換する。
     *
     * @param date 日付
     * @return String。ただし null が与えられたら null を返す。
     */
    public static String convertFromLocalDateToString(LocalDate date) {
        String result = null;
        if (Objects.nonNull(date)) {
            result = date.format(getFormatter(CommonConst.LOCAL_DATE_STYLE));
        }
        return result;
    }

    /**
     * String から LocalTimeに変換する。
     *
     * @param k String
     * @return LocalTime。ただし null や空文字が与えられたら null を返す。
     */
    public static LocalTime convertFromStringToLocalTime(String k) {
        LocalTime result = null;
        if (StringUtils.isNotEmpty(k)) {
            result = LocalTime.parse(k, getFormatter(CommonConst.LOCAL_TIME_STYLE));
        }
        return result;
    }

    /**
     * LocalTime から Stringに変換する。
     *
     * @param time 時間
     * @return String。ただし null が与えられたら null を返す。
     */
    public static String convertFromLocalTimeToString(LocalTime time) {
        String result = null;
        if (Objects.nonNull(time)) {
            result = time.format(getFormatter(CommonConst.LOCAL_TIME_STYLE));
        }
        return result;
    }

    /**
     * LocalDateTime から String に変換する。（yyyy年MM月dd日）
     *
     * @param datetime 日時
     * @return String。ただし null が与えられたら null を返す。
     */
    public static String convertFromLocalDateTimeToStringJp(LocalDateTime datetime) {
        String result = null;
        if (Objects.nonNull(datetime)) {
            result = datetime.format(getFormatter(CommonConst.LOCAL_DATE_CN_STYLE));
        }
        return result;
    }

    /**
     * 対象のDateTimeFormatterを取得する。
     *
     * @param style 日付時刻フォーマッタのスタイル
     * @return DateTimeFormatter。
     * @exception IllegalArgumentException 日付時刻フォーマッタのスタイルが不正な場合
     */
    public static DateTimeFormatter getFormatter(String style) {
        if (StringUtils.isBlank(style)) {
            throw new IllegalArgumentException("日付時刻のスタイルが不正です。");
        }
        return DateTimeFormatter.ofPattern(style).withResolverStyle(ResolverStyle.STRICT);
    }

}
