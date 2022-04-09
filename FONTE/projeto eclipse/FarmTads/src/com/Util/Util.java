
package com.Util;

import java.util.regex.*;

public class Util
{

   public static boolean textoTemSoNumeros( String numero )
   {
      return Pattern.matches( "\\d+", numero );
   }   

}
