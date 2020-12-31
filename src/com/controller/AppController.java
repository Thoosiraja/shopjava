package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.InvoiceDAO;
import com.dao.InvoiceTransDAO;
import com.dao.ItemDAO;
import com.dao.UserDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.service.AdminService;
import com.service.LoginService;
import com.service.ShopingService;
import com.service.UserService;
import com.tables.Invoice_Master;
import com.tables.Invoice_Transaction;
import com.tables.Item_Master;
import com.tables.User_Master;

@Controller
@RequestMapping("/")
@ComponentScan("com")	
public class AppController {
	@Autowired
	LoginService login;
	@Autowired
	UserService user;
	@Autowired
	AdminService admin;
	@Autowired
	ShopingService shop;
	@Autowired
	UserDAO userdao;
	@Autowired
	ItemDAO itemdao;
	@Autowired
	InvoiceDAO invoicedao;
	@Autowired
	InvoiceTransDAO invdao;
	@RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
	public String loginget()
	{
		return "index";
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public String loginpost(HttpServletRequest request,ModelMap model)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int result = login.userlogin(username, password);
		HttpSession session = request.getSession();
		int userid = login.getUserid(username, password);
		session.setAttribute("userid", userid);
		if(result == 1)
		{
			return "welcome";
		}
		else if(result == 2)
		{
			return "aldreadylogged";
		}
		model.addAttribute("error", new String("Invalid UserName or Password"));
		return "index";
	}
	@RequestMapping(value = {"/adminlogin"}, method = RequestMethod.GET)
	public String adminloginget()
	{
		return "adminlogin";
	}
	
	@RequestMapping(value = {"/adminlogin"}, method = RequestMethod.POST)
	public String adminloginpost(HttpServletRequest request,ModelMap model)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int result = login.adminlogin(username, password);
		HttpSession session = request.getSession();
		int userid = login.getUserid(username, password);
		session.setAttribute("userid", userid);
		if(result == 1)
		{
			return "adminwelcome";
		}
		else if(result == 2)
		{
			return "aldreadylogged";
		}
		model.addAttribute("error", new String("Invalid UserName or Password"));
		return "adminlogin";
	}
	
	@RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		System.out.println("User Id " + session.getAttribute("userid"));
		login.changeFlag((int) session.getAttribute("userid"));
		return "index";
	}
	@RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
	public String logout1(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		System.out.println("User Id " + session.getAttribute("userid"));
		login.changeFlag((int) session.getAttribute("userid"));
		return "index";
	}
	@RequestMapping(value = "/registeruser" ,method = RequestMethod.GET)
	public String userregisterget()
	{
		return "register";
	}
	
	@RequestMapping(value = "/registeruser" ,method = RequestMethod.POST)
	public String userregisterpost(HttpServletRequest request)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		user.createUser(username, password, "user", city, street);
		return "index";
	}
	@RequestMapping(value = "/registeradmin" ,method = RequestMethod.GET)
	public String adminregisterget()
	{
		return "adminregister";
	}
	
	@RequestMapping(value = "/registeradmin" ,method = RequestMethod.POST)
	public String adminregisterpost(HttpServletRequest request)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		user.createUser(username, password, "admin", city, street);
		return "adminwelcome";
	}
	@RequestMapping(value = "/additem" ,method = RequestMethod.GET)
	public String additemget()
	{
		return "additem";
	}
	
	@RequestMapping(value = "/additem" ,method = RequestMethod.POST)
	public String additempost(HttpServletRequest request) throws Exception
	{
		String itemdesc = request.getParameter("itemdesc");
		String categories = request.getParameter("categories");
		String unit = request.getParameter("unit");
		String tempprize = request.getParameter("prize");
		float prize = Float.parseFloat(tempprize);
		File img = new File(request.getParameter("image"));
		byte[] image = new byte[(int)img.length()];
		try {
	         FileInputStream fileInputStream = new FileInputStream(img);
	         fileInputStream.read(image);
	         fileInputStream.close();
	        } catch (Exception e) {
	         e.printStackTrace();
	        }
		admin.createItem(itemdesc, categories, unit, prize, image);
		return "adminwelcome";
	}
	
	@RequestMapping(value = "/displayitem" ,method = RequestMethod.GET)
	public String displayitemget(ModelMap model) throws Exception
	{
		List<Item_Master> itemsorg = shop.getAllItem();
		Iterator<Item_Master> ite = itemsorg.iterator();
		List<Item> items = new ArrayList<Item>();
		while(ite.hasNext())
		{
			Item_Master item = ite.next();
			Item itemcpy = new Item();
			itemcpy.setCategories(item.getCategories());
			itemcpy.setItemdesc(item.getItemdesc());
			itemcpy.setItemno(item.getItemno());
			itemcpy.setPrize(item.getPrize());
			itemcpy.setUnit(item.getUnit());
			byte[] encodeBase64 = Base64.encodeBase64(item.getImage());
            itemcpy.setImage(new String(encodeBase64, "UTF-8"));
            items.add(itemcpy);
		}
		model.addAttribute("items", items); 
		return "displayitem";
	}
	
	@RequestMapping(value = "/shop" ,method = RequestMethod.POST)
	public String shop(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception
	{
		HttpSession session = request.getSession();
		int len = shop.getAllItem().size();
		int[][] shopcart = new int[len][2];
		int flag = 0;
		for(int i=0;i<len;i++)
		{
			shopcart[i][0] = i+1;
			shopcart[i][1] = Integer.parseInt(request.getParameter(String.valueOf(i+1)));
			if(shopcart[i][1] != 0)
			{
				flag++;
			}
		}
		if(flag == 0)
		{
			return displayitemget(model);
		}
		int userid = (int) session.getAttribute("userid");
		int invoiceno = shop.createInvoice(userid,len,shopcart);
		Invoice_Master invoice = invoicedao.getInvoice(invoiceno);
		User_Master user = invoice.getUser();
		String path = request.getRealPath("/");
		File pdf = new File(path+"Invoice_"+invoice.getInvno()+".pdf");
		System.out.println(pdf.getPath());
		System.out.println("File created "+ pdf.getAbsolutePath()+""+pdf.exists()+""+pdf.isFile());
		Document doc = new Document();
		try {
			PdfWriter docWriter =PdfWriter.getInstance(doc , new FileOutputStream(path+"Invoice_"+invoice.getInvno()+".pdf"));
			doc.open();
			Paragraph para = new Paragraph("Invoice Number : "+invoice.getInvno());
			doc.add(para);
			para = new Paragraph("Invoice Date : "+invoice.getDate());
			doc.add(para);
			para = new Paragraph("Customer ID : " + user.getUserid());
			doc.add(para);
			para = new Paragraph("Customer Name : " + user.getUsername());
			doc.add(para);
			PdfPTable table = new PdfPTable(new float[]{2f, 2f, 2f, 2f, 2f, 2f});
			table.addCell("Item ID");
			table.addCell("Item name");
			table.addCell("Unit");
			table.addCell("Price");
			table.addCell("Quantity");
			table.addCell("Net");
			
			Set<Invoice_Transaction> inv = invoice.getInvtranactions();
			System.out.println("Size : "+inv.size());
			Iterator<Invoice_Transaction> iter = inv.iterator();
			while(iter.hasNext())
			{
				System.out.println("I am while ");
				Invoice_Transaction invtrans = iter.next();
				Item_Master item = invtrans.getItem();
				table.addCell(String.valueOf(item.getItemno()));
				table.addCell(item.getItemdesc());
				table.addCell(item.getUnit());
				table.addCell(String.valueOf(item.getPrize()));
				table.addCell(String.valueOf(invtrans.getQty()));
				float net = invtrans.getQty() * item.getPrize();
				table.addCell(String.valueOf(net));
			}
			doc.add(table);
			para = new Paragraph("Total : " + invoice.getTotal());
			doc.add(para);
			doc.close();
			docWriter.close();
		}catch(Exception e) {e.printStackTrace();}

		response.setContentType("APPLICATION/OCTET-STREAM");
		try {
	        PrintWriter out = response.getWriter();
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + pdf.getName() + "\"");
	        FileInputStream f = new FileInputStream(pdf);
	        int i;
	        while ((i = f.read()) != -1) {
	            out.write(i);
	        }
	        f.close();
	        out.close();
	        return "welcome";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "welcome";
	}
	
	@RequestMapping(value = "/userinvoice" ,method = RequestMethod.GET)
	public String displayinvoices(ModelMap model,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		List<Invoice_Master> invoices = admin.getInvoicesOfCustomer(userid);
		if(invoices.size() > 0)
		{
			model.addAttribute("invoices", invoices);
		}
		else
		{
			model.addAttribute("error", new String("No Invoices Found !"));
		}
		return "displayuserinvoice";
	}
	@RequestMapping(value = "/getinvoice" ,method = RequestMethod.POST)
	public String displayinvoicespost(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		int invoiceno = Integer.parseInt(request.getParameter("invoiceid"));
		Invoice_Master invoice = invoicedao.getInvoice(invoiceno);
		User_Master user = invoice.getUser();
		String path = request.getRealPath("/");
		File pdf = new File(path+"Invoice_"+invoice.getInvno()+".pdf");
		System.out.println(pdf.getPath());
		System.out.println("File created "+ pdf.getAbsolutePath()+""+pdf.exists()+""+pdf.isFile());
		Document doc = new Document();
		try {
			PdfWriter docWriter =PdfWriter.getInstance(doc , new FileOutputStream(path+"Invoice_"+invoice.getInvno()+".pdf"));
			doc.open();
			Paragraph para = new Paragraph("Invoice Number : "+invoice.getInvno());
			doc.add(para);
			para = new Paragraph("Invoice Date : "+invoice.getDate());
			doc.add(para);
			para = new Paragraph("Customer ID : " + user.getUserid());
			doc.add(para);
			para = new Paragraph("Customer Name : " + user.getUsername());
			doc.add(para);
			PdfPTable table = new PdfPTable(new float[]{2f, 2f, 2f, 2f, 2f, 2f});
			table.addCell("Item ID");
			table.addCell("Item name");
			table.addCell("Unit");
			table.addCell("Price");
			table.addCell("Quantity");
			table.addCell("Net");
			
			Set<Invoice_Transaction> inv = invoice.getInvtranactions();
			System.out.println("Size : "+inv.size());
			Iterator<Invoice_Transaction> iter = inv.iterator();
			while(iter.hasNext())
			{
				System.out.println("I am while ");
				Invoice_Transaction invtrans = iter.next();
				Item_Master item = invtrans.getItem();
				table.addCell(String.valueOf(item.getItemno()));
				table.addCell(item.getItemdesc());
				table.addCell(item.getUnit());
				table.addCell(String.valueOf(item.getPrize()));
				table.addCell(String.valueOf(invtrans.getQty()));
				float net = invtrans.getQty() * item.getPrize();
				table.addCell(String.valueOf(net));
			}
			doc.add(table);
			para = new Paragraph("Total : " + invoice.getTotal());
			doc.add(para);
			doc.close();
			docWriter.close();
		}catch(Exception e) {e.printStackTrace();}
		response.setContentType("APPLICATION/OCTET-STREAM");
		try {
	        PrintWriter out = response.getWriter();
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + pdf.getName() + "\"");
	        FileInputStream f = new FileInputStream(pdf);
	        int i;
	        while ((i = f.read()) != -1) {
	            out.write(i);
	        }
	        f.close();
	        out.close();
	        return "welcome";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "displayuserinvoice";
	}

	@RequestMapping(value = "/checkinvoice" ,method = RequestMethod.GET)
	public String userinvoiceget(ModelMap model,HttpServletRequest request)
	{
		return "userinvoice";
	}
	@RequestMapping(value = "/checkinvoice" ,method = RequestMethod.POST)
	public String userinvoice(ModelMap model,HttpServletRequest request,HttpServletResponse response)
	{
		int userid = Integer.parseInt(request.getParameter("userid"));
		List<Invoice_Master> invoices = admin.getInvoicesOfCustomer(userid);
		if(invoices.size() > 0)
		{
			String path = request.getRealPath("/");
			File CustomerReport = new File(path+"CustomerReport.xls");
			try {
				HSSFWorkbook workbook = new HSSFWorkbook();   
				HSSFSheet sheet = workbook.createSheet("CustomerReport");   
				HSSFRow rowhead = sheet.createRow((short)0);  
				rowhead.createCell(0).setCellValue("Invoice Number");  
				rowhead.createCell(2).setCellValue("Invoice Date");  
				rowhead.createCell(3).setCellValue("Invoice Amount");  
				for(int i=0;i<invoices.size();i++) {
				    rowhead = sheet.createRow((short)(i+1)); 
						rowhead.createCell(0).setCellValue(invoices.get(i).getInvno()); 
						rowhead.createCell(2).setCellValue(invoices.get(i).getDate().toString()); 
						rowhead.createCell(3).setCellValue(invoices.get(i).getTotal()); 
				}
				FileOutputStream fileOut = new FileOutputStream(path+"CustomerReport.xls");   
				workbook.write(fileOut); 
				workbook.close();
				fileOut.close(); 
				response.setContentType("APPLICATION/OCTET-STREAM");
				try {
			        PrintWriter out = response.getWriter();
			        response.setHeader("Content-Disposition", "attachment; filename=\"" + CustomerReport.getName() + "\"");
			        FileInputStream fl = new FileInputStream(CustomerReport);
			        int i;
			        while ((i = fl.read()) != -1) {
			            out.write(i);
			        }
			        fl.close();
			        out.close();
			        CustomerReport.delete();
			        System.out.println("File Out for downlaod and has been deleted in the server Storage..!");
				}catch(Exception e) {e.printStackTrace();}
			}catch(Exception e) {e.printStackTrace();}
		}
		else
		{
			model.addAttribute("error", new String("No Invoice Found !"));
		}
		return "userinvoice";
	}
	@RequestMapping(value = "/invoicebetweendate" ,method = RequestMethod.GET)
	public String dateinvoiceget(ModelMap model,HttpServletRequest request)
	{
		return "invoicedates";
	}
	@RequestMapping(value = "/invoicebetweendate" ,method = RequestMethod.POST)
	public String dateinvoicepost(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Date start = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("start"));
		Date end = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("end"));
		List<Invoice_Master> invoices =  admin.getInvoicesBetweenTwoDates(start, end);
		if(invoices.size() > 0)
		{
			String path = request.getRealPath("/");
			File Report = new File(path+"Report.xls");
			try {
				HSSFWorkbook workbook = new HSSFWorkbook();   
				HSSFSheet sheet = workbook.createSheet("Report");   
				HSSFRow rowhead = sheet.createRow((short)0);  
				rowhead.createCell(0).setCellValue("Invoice Number");  
				rowhead.createCell(2).setCellValue("Invoice Date");  
				rowhead.createCell(3).setCellValue("Invoice Amount");
				rowhead.createCell(4).setCellValue("User Id");
				rowhead.createCell(5).setCellValue("User Name");
				for(int i=0;i<invoices.size();i++) {
				    rowhead = sheet.createRow((short)(i+1)); 
						rowhead.createCell(0).setCellValue(invoices.get(i).getInvno()); 
						rowhead.createCell(2).setCellValue(invoices.get(i).getDate().toString()); 
						rowhead.createCell(3).setCellValue(invoices.get(i).getTotal());
						User_Master user = invoices.get(i).getUser();
						rowhead.createCell(4).setCellValue(user.getUserid());
						rowhead.createCell(5).setCellValue(user.getUsername());
				}
				FileOutputStream fileOut = new FileOutputStream(path+"Report.xls");   
				workbook.write(fileOut); 
				workbook.close();
				fileOut.close(); 
				response.setContentType("APPLICATION/OCTET-STREAM");
				try {
			        PrintWriter out = response.getWriter();
			        response.setHeader("Content-Disposition", "attachment; filename=\"" + Report.getName() + "\"");
			        FileInputStream fl = new FileInputStream(Report);
			        int i;
			        while ((i = fl.read()) != -1) {
			            out.write(i);
			        }
			        fl.close();
			        out.close();
			        Report.delete();
				}catch(Exception e) {e.printStackTrace();}
			}catch(Exception e) {e.printStackTrace();}
		}
		else
		{
			model.addAttribute("error", new String("No Invoice Found !"));
		}
		return "invoicedates";
	}
}
