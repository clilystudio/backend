package com.wistronits.wistlotto.framework.mybatis;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * Mybatis注解生成插件
 * 
 * @author 盛广立 2019年1月16日
 */
public class MapperAnnotationPlugin extends PluginAdapter {
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper")); //$NON-NLS-1$
		interfaze.addAnnotation("@Mapper"); //$NON-NLS-1$
		return true;
	}
}
