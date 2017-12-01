package com.lawyer.manage.system.enums;

/**
 * 菜单类型枚举
 * @author gzp
 *
 */
public enum MenuTypeEnum {
    /**
     正常	0
     异常	1
     */
	Module(1, "模块"),
	Menu(2, "菜单"),
	Function(3, "功能");
    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    MenuTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public static MenuTypeEnum valuesOf(int index) {
        switch (index) {
            case 1:
                return Module;
            case 2:
                return Menu;
            case 3:
                return Function;
            default:
                return null;
        }
    }
}
