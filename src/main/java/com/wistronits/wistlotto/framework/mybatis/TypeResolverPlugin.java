package com.wistronits.wistlotto.framework.mybatis;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Properties;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

public class TypeResolverPlugin extends JavaTypeResolverDefaultImpl {
    /**
    *
    * {@inheritDoc}
    */
   public void addConfigurationProperties(Properties properties) {
       super.addConfigurationProperties(properties);

       // Integer -> BigDecimal
       typeMap.put(Types.INTEGER, new JdbcTypeInformation("INTEGER", //$NON-NLS-1$
               new FullyQualifiedJavaType(BigDecimal.class.getName())));

       // Date -> LocalDate
       typeMap.put(Types.DATE, new JdbcTypeInformation("DATE", //$NON-NLS-1$
               new FullyQualifiedJavaType(LocalDate.class.getName())));

       // TimeStamp -> LocalDateTime
       typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", //$NON-NLS-1$
               new FullyQualifiedJavaType(LocalDateTime.class.getName())));

       // Time -> LocalTime
       typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", //$NON-NLS-1$
               new FullyQualifiedJavaType(LocalTime.class.getName())));
   }
}
