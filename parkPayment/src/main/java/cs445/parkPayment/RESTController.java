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
	@Path("/parks{pid}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPark(@Context UriInfo uriInfo,
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
	@Path("/parks/{pid")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPark(@Context UriInfo uriInfo,
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
	
}
