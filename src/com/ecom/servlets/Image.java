package com.ecom.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Image", urlPatterns = { "/images/*" }, initParams = @WebInitParam( name = "path", value = "/home/alan/Documents/RICM5/eCOM-RICM5-2015/file/images/" ) )
public class Image extends HttpServlet {
    public static final int BUFFER_SIZE = 10240; // 10ko

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /*
         * Lecture du paramètre 'path' passé à la servlet via la déclaration
         * dans le web.xml
         */
        String path = this.getServletConfig().getInitParameter( "path" );

        /*
         * Récupération du path du file demandé au sein de l'URL de la
         * requête
         */
        String requiredFile = request.getPathInfo();

        /* Vérifie qu'un file a bien été fourni */
        if ( requiredFile == null || "/".equals( requiredFile ) ) {
            /*
             * Si non, alors on envoie une erreur 404, qui signifie que la
             * ressource demandée n'existe pas
             */
            response.sendError( HttpServletResponse.SC_NOT_FOUND );
            return;
        }

        /*
         * Décode le nom de file récupéré, susceptible de contenir des
         * espaces et autres caractères spéciaux, et prépare l'objet File
         */
        requiredFile = URLDecoder.decode( requiredFile, "UTF-8" );
        File file = new File( path, requiredFile );

        /* Vérifie que le file existe bien */
        if ( !file.exists() ) {
            /*
             * Si non, alors on envoie une erreur 404, qui signifie que la
             * ressource demandée n'existe pas
             */
            response.sendError( HttpServletResponse.SC_NOT_FOUND );
            return;
        }

        /* Récupère le type du file */
        String type = getServletContext().getMimeType( file.getName() );

        /*
         * Si le type de file est inconnu, alors on initialise un type par
         * défaut
         */
        if ( type == null ) {
            type = "application/octet-stream";
        }

        /* Initialise la réponse HTTP */
        response.reset();
        response.setBufferSize( BUFFER_SIZE );
        response.setContentType( type );
        response.setHeader( "Content-Length", String.valueOf( file.length() ) );
        response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );

        /* Prépare les flux */
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            /* Ouvre les flux */
        	entree = new BufferedInputStream( new FileInputStream( file ), BUFFER_SIZE );
        	sortie = new BufferedOutputStream( response.getOutputStream(), BUFFER_SIZE );

            /* Lit le file et écrit son contenu dans la réponse HTTP */
            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            while ( ( length = entree.read( buffer ) ) > 0 ) {
            	sortie.write( buffer, 0, length );
            }
        } finally {
            try {
            	sortie.close();
            } catch ( IOException ignore ) {
            }
            try {
                entree.close();
            } catch ( IOException ignore ) {
            }
        }
    }
}