package cs445.parkPayment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import datastructs.*;
import entities.*;
import interfaces.BoundaryInterface;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/parkpay")
public class RESTController {
	private BoundaryInterface bi = new ParkManager();
	
	@Path("/parks")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewAllParks() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(bi.viewAllParks());
        return Response.status(Response.Status.OK).entity(s).build();
	}
	@Path("/parks")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPark(@Context UriInfo uriInfo,
			@QueryParam("name") String name, 
			@QueryParam("street") String street,
			@QueryParam("city") String city,
			@QueryParam("state") String state,
			@QueryParam("zip") String zip,
			@QueryParam("website") String website,
			@QueryParam("lat") double lat,
			@QueryParam("lng") double lng,
			@QueryParam("rvi") int rvi,
			@QueryParam("rvo") int rvo,
			@QueryParam("ci") int ci,
			@QueryParam("co") int co,
			@QueryParam("mi") int mi,
			@QueryParam("mo") int mo) {
		Address addr = new Address(street, city, state, zip);
		Geo geo = new Geo(lat, lng);
		int[][] paymentInfo = { {rvi, rvo}, {ci, co}, {mi,mo} };
		Park p = bi.createPark(name, addr, website, geo, paymentInfo);
		
		int id = p.getPid();
		Gson gson = new Gson();
		String s = gson.toJson(p);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(id));
        
		return Response.created(builder.build()).entity(s).build();
	}
	@Path("/parks")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPark(@Context UriInfo uriInfo,
			@QueryParam("name") String name, 
			@QueryParam("region") String region,
			@QueryParam("street") String street,
			@QueryParam("city") String city,
			@QueryParam("state") String state,
			@QueryParam("zip") String zip,
			@QueryParam("phone") String phone,
			@QueryParam("website") String website,
			@QueryParam("lat") double lat,
			@PathParam("lng") double lng,
			@QueryParam("rvi") int rvi,
			@QueryParam("rvo") int rvo,
			@QueryParam("ci") int ci,
			@QueryParam("co") int co,
			@QueryParam("mi") int mi,
			@QueryParam("mo") int mo) {
		Address addr = new Address(street, city, state, zip);
		Geo geo = new Geo(lat, lng);
		int[][] paymentInfo = { {rvi, rvo}, {ci, co}, {mi,mo} };
		Park p = bi.createPark(name, region, addr, website, phone, geo, paymentInfo);
		
		int id = p.getPid();
		Gson gson = new Gson();
		String s = gson.toJson(p);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(id));
        
		return Response.created(builder.build()).entity(s).build();
	}
	@Path("/parks")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePark(@Context UriInfo uriInfo,
			@PathParam("pid") int pid,
			@QueryParam("name") String name, 
			@QueryParam("street") String street,
			@QueryParam("city") String city,
			@QueryParam("state") String state,
			@QueryParam("zip") String zip,
			@QueryParam("website") String website,
			@QueryParam("lat") double lat,
			@QueryParam("lng") double lng,
			@QueryParam("rvi") int rvi,
			@QueryParam("rvo") int rvo,
			@QueryParam("ci") int ci,
			@QueryParam("co") int co,
			@QueryParam("mi") int mi,
			@QueryParam("mo") int mo) {
		Address addr = new Address(street, city, state, zip);
		Geo geo = new Geo(lat, lng);
		int[][] paymentInfo = { {rvi, rvo}, {ci, co}, {mi,mo} };
		bi.updatePark(pid, name, addr, website, geo, paymentInfo);
        
		return Response.ok().build();
	}
	@Path("/parks/{pid}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePark(@Context UriInfo uriInfo,
			@PathParam("pid") int pid,
			@QueryParam("name") String name, 
			@QueryParam("region") String region,
			@QueryParam("street") String street,
			@QueryParam("city") String city,
			@QueryParam("state") String state,
			@QueryParam("zip") String zip,
			@QueryParam("phone") String phone,
			@QueryParam("website") String website,
			@QueryParam("lat") double lat,
			@QueryParam("lng") double lng,
			@QueryParam("rvi") int rvi,
			@QueryParam("rvo") int rvo,
			@QueryParam("ci") int ci,
			@QueryParam("co") int co,
			@QueryParam("mi") int mi,
			@QueryParam("mo") int mo) {
		Address addr = new Address(street, city, state, zip);
		Geo geo = new Geo(lat, lng);
		int[][] paymentInfo = { {rvi, rvo}, {ci, co}, {mi,mo} };
		bi.updatePark(pid, name, region, addr, website, phone, geo, paymentInfo);

		return Response.ok().build();
	}
	@Path("parks/{pid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePark(@PathParam("pid") int pid) {
		bi.deletePark(pid);
		return Response.ok().build();
	}
	@Path("parks/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewParkDetail(@PathParam("pid") int pid) {
        Park p;
        try{
        	p = bi.viewParkDetail(pid);
        }
        catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + pid).build();
        } 
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(p);
        return Response.ok(s).build();
	}
	@Path("parks?key={key}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchParks(@PathParam("key") String key){
		Gson gson = new Gson();
		String s = gson.toJson(bi.searchParks(key));
		return Response.ok(s).build();
	}
	@Path("parks/{pid}/notes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewParkNotes(@PathParam("pid") int pid) {
		Gson gson = new Gson();
		String s = gson.toJson(bi.viewParkNotes(pid));
		return Response.ok(s).build();
	}
	@Path("parks/{pid}/notes/{nid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewParkNotes(
			@PathParam("pid") int pid,
			@PathParam("nid") int nid) {
		Gson gson = new Gson();
		String s = gson.toJson(bi.viewParkNoteDetail(pid, nid));
		return Response.ok(s).build();
	}
	@Path("parks/{pid}/notes")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNoteForPark(@Context UriInfo uriInfo,
			@PathParam("pid") int pid,
			@QueryParam("vid") int vid,
			@QueryParam("title") String title,
			@QueryParam("text") String text) {
		Note n = bi.createNoteForPark(pid, vid, title, text);
		
		int id = n.getNid();
		Gson gson = new Gson();
		String s = gson.toJson(n);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(id));
        
		return Response.created(builder.build()).entity(s).build();
	
	}
	@Path("parks/{pid}/notes")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNoteForPark(@Context UriInfo uriInfo,
			@PathParam("pid") int pid,
			@QueryParam("vid") int vid,
			@QueryParam("title") String title,
			@QueryParam("text") String text,
			@QueryParam("month") int month,
			@QueryParam("day") int day,
			@QueryParam("year") int year)
	{
		Date d = new Date(month, day, year);
		Note n = bi.createNoteForPark(pid, vid, d, title, text);
		
		int id = n.getNid();
		Gson gson = new Gson();
		String s = gson.toJson(n);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(id));
        
		return Response.created(builder.build()).entity(s).build();
	
	}
	@Path("notes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewAllNotes() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(bi.viewAllNotes());
        return Response.status(Response.Status.OK).entity(s).build();
	}
	@Path("notes/{nid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewNoteDetail(@PathParam("nid") int nid) {
        Note n;
        try{
        	n = bi.viewNoteDetail(nid);
        }
        catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + nid).build();
        } 
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(n);
        return Response.ok(s).build();
	}
	@Path("notes/{nid}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateNote(@PathParam("nid") int nid,
			@QueryParam("vid") int vid,
			@QueryParam("title") String title,
			@QueryParam("text") String text) {
		bi.updateNote(nid, vid, title, text);
		return Response.ok().build();
	}
	@Path("notes/{nid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteNote(@PathParam("nid") int nid) {
		bi.deleteNote(nid);
		return Response.ok().build();
	}
	@Path("notes?key={key}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchNotes(@PathParam("key") String key){
		Gson gson = new Gson();
		String s = gson.toJson(bi.searchNotes(key));
		return Response.ok(s).build();
	}
	@Path("orders")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createOrder(@Context UriInfo uriInfo,
			@QueryParam("pid") int pid,
			@QueryParam("vehicleType") String vType,
			@QueryParam("lpNum") String lpNum,
			@QueryParam("lpState") String lpState,
			@QueryParam("vid") int vid,
			@QueryParam("ccName") String ccName,
			@QueryParam("ccNum") String ccNum,
			@QueryParam("ccExpdate") String ccExpDate,
			@QueryParam("ccBillStreet") String ccBillStreet,
			@QueryParam("ccBillCity") String ccBillCity,
			@QueryParam("ccBillState") String ccBillState,
			@QueryParam("ccBillZip") String ccBillZip) {
		Visitor vi = bi.viewVisitorDetail(vid);
		Vehicle veh = new Vehicle(vType, new LicensePlate(lpNum, lpState));
		Address a = new Address(ccBillStreet, ccBillCity, ccBillState, ccBillZip);
		CreditCard cc = new CreditCard(ccNum, ccName, ccExpDate, a);
		
		Order o = bi.createOrder(pid, veh, vi, cc);
		
		int id = o.getOid();
		Gson gson = new Gson();
		String s = gson.toJson(o);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(id));
        
		return Response.created(builder.build()).entity(s).build();
	}
	@Path("orders")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewAllOrders() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(bi.viewAllOrders());
        return Response.status(Response.Status.OK).entity(s).build();
	}
	@Path("orders/{oid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewOrderDetail(@PathParam("oid") int oid) {
		Order o; 
		try{
			o = bi.viewOrderDetails(oid);        
		}
        catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + oid).build();
        } 
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(o);
        return Response.ok(s).build();
	}
	@Path("orders?key={key}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchOrders(@PathParam("key") String key) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(bi.searchOrders(key));
        return Response.status(Response.Status.OK).entity(s).build();
	}
	@Path("visitors")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createVisitor(@Context UriInfo uriInfo,
			@PathParam("email") String email) {
		Visitor v = bi.createVisitor(email);
		int id = v.getVid();
		Gson gson = new Gson();
		String s = gson.toJson(v);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(id));
        
		return Response.created(builder.build()).entity(s).build();
	}
	@Path("visitors")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createVisitor(@Context UriInfo uriInfo,
			@PathParam("email") String email,
			@PathParam("name") String name) {
		Visitor v = bi.createVisitor(name, email);
		int id = v.getVid();
		Gson gson = new Gson();
		String s = gson.toJson(v);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(id));
        
		return Response.created(builder.build()).entity(s).build();
	}
	@Path("visitors")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewAllVisitors() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(bi.viewAllVisitors());
        return Response.status(Response.Status.OK).entity(s).build();
	}
	@Path("visitors/{vid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewVisitorDetail(@PathParam("vid") int vid) {
		Visitor v; 
		try{
			v = bi.viewVisitorDetail(vid);        
		}
        catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + vid).build();
        } 
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(v);
        return Response.ok(s).build();
	}
	@Path("visitors?key={key}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchVisitors(@PathParam("key") String key) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(bi.searchVisitors(key));
        return Response.status(Response.Status.OK).entity(s).build();
	}
	@Path("reports")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createReport(@Context UriInfo uriInfo,
			@QueryParam("name") String name,
			@QueryParam("startMonth") int sm,
			@QueryParam("startDay") int sd,
			@QueryParam("staryYear") int sy,
			@QueryParam("endMonth") int em,
			@QueryParam("endDay") int ed,
			@QueryParam("endYear") int ey) {
		Date start = new Date(sm, sd, sy);
		Date end = new Date(em, ed, ey);
		Report r = bi.createReport(name, start, end);
		
		int id = r.getRid();
		Gson gson = new Gson();
		String s = gson.toJson(r);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(id));
        
		return Response.created(builder.build()).entity(s).build();
	
	}
	@Path("reports")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewAllReports() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(bi.viewAllReports());
        return Response.status(Response.Status.OK).entity(s).build();
	}
	@Path("visitors/{rid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewReportDetail(@PathParam("rid") int rid) {
		Report r; 
		try{
			r = bi.viewReportDetail(rid);        
		}
        catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + rid).build();
        } 
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(r);
        return Response.ok(s).build();
	}
	@Path("search?key={key}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(@PathParam("key") String key) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(bi.search(key));
        return Response.status(Response.Status.OK).entity(s).build();
	
	}
}
