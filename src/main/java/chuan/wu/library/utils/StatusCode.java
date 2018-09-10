/*
 * Copyright (c) 2018.
 */

package chuan.wu.library.utils;

import lombok.Getter;

@Getter
public enum StatusCode {
   PARAMETER_ERROR(400),
   INTERNAL_ERROR(500),
   TIME_OUT(500);

   private int code;

   StatusCode(Integer code) {
      this.code = code;
   }
}
