package br.com.gestaoambiental.util;

import org.hibernate.cfg.Configuration;

/**
 * 
 * Classe usada para gerar uma tabela SQL segundo as anota��es em JPA que est�o
 * nas classes Beans da aplica��o
 * 
 * OBS:
 * Ela apaga e recria a base de dados
 * 
 * Executar m�todo main
 * 
 */
public class SqlUtil
{

   public static void main( String[ ] args )
   {

      Configuration cfg = new Configuration( );
      cfg.configure( "hibernate.cfg.xml" );

      //new SchemaExport( cfg ).create( true, true );

   }

}
