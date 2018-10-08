package com.wistronits.wistlotto.framework.mybatis;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

public class RenameMapperClassPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void initialized(IntrospectedTable table) {
        super.initialized(table);
        String name = table.getMyBatis3JavaMapperType();
        table.setMyBatis3JavaMapperType(name.replaceAll("Mapper$", "Repository")); //$NON-NLS-1$
    }
}
