/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support;

import java.nio.charset.StandardCharsets;
import java.sql.JDBCType;
import java.sql.Types;

/**
 * Data type Helper
 *
 * @author ebrimatunkara
 */
public class DataTypeHelper {

    public static byte[] stringToByte(String text) {
        return (text != null) ? text.getBytes(StandardCharsets.UTF_8) : null;
    }

    public static String bytesToString(byte[] bytes) {
        return (bytes != null) ? new String(bytes, StandardCharsets.UTF_8) : null;
    }
}
