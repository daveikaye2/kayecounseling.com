package com.mom;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LoadDocumentServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BufferedInputStream  bis = null;
		BufferedOutputStream bos = null;
		try {
			String fileName = req.getParameter("name");
			resp.setContentType("application/pdf");
			resp.setHeader("Content-disposition",
	                "attachment; filename=" + fileName);
			
			FileInputStream file = new FileInputStream(getServletContext().getRealPath("/WEB-INF/documents/"+fileName));
			bis = new BufferedInputStream(file);
			bos = new BufferedOutputStream(resp.getOutputStream());
			
			byte[] buff = new byte[2048];
		    int bytesRead; 
		    // Simple read/write loop.
		    while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
		        bos.write(buff, 0, bytesRead);
		    }
	    
		} catch(final MalformedURLException e) {
	        System.out.println ( "MalformedURLException." );
	        throw e;
	    } catch(final IOException e) {
	        System.out.println ( "IOException." );
	        throw e;
	    } finally {
	        if (bis != null)
	            bis.close();
	        if (bos != null)
	            bos.close();
	    }
	}
}
