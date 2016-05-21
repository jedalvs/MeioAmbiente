package br.com.gestaoambiental.util;

import org.hibernate.cfg.Configuration;

/**
 * 
 * Classe usada para gerar uma tabela SQL segundo as anotações em JPA que estão
 * nas classes Beans da aplicação
 * 
 * OBS:
 * Ela apaga e recria a base de dados
 * 
 * Executar método main
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
