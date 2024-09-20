package org.ritpl.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.ritpl.dto.CarRequest;
import org.ritpl.dto.CarResponse;
import org.ritpl.entity.Car;
import org.ritpl.services.CarService;

import java.util.List;

@Path("/cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarResource {

    @Inject
    private CarService carService;

    @POST
    @Path("/")
    public Response addCar(@RequestBody CarRequest carRequest) {
        String cars = carService.addCar(carRequest);
        return Response.status(Response.Status.CREATED).entity(cars).build();
    }

    @GET
    @Path("/{Id}")
    public Response getCarById(@PathParam("Id")Long Id) {
        CarResponse car = carService.getCarById(Id);
        return Response.status(Response.Status.FOUND).entity(car).build();
    }

    @PUT
    @Path("/{Id}")
    public Response getCarById(@PathParam("Id")Long Id,@RequestBody CarRequest carRequest) {
        String car = carService.updateCarById(Id,carRequest);
        return Response.status(Response.Status.OK).entity(car).build();
    }

    @DELETE
    @Path("/{Id}")
    public Response deleteById (@PathParam("Id")Long Id){
        String car=carService.deleteCarById(Id);
       return Response.status(Response.Status.OK).entity(car).build();
    }

    @GET
    @Path("/")
    public Response getAllCars(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("sortField") @DefaultValue("carName") String sortField,
            @QueryParam("sortOrder") @DefaultValue("asc") String sortOrder,
            @QueryParam("carName") String carName,
            @QueryParam("company") String company) {
        List<Car> cars = carService.getAllCarsWithPaginationSortingAndFiltering(page,size,sortField,sortOrder,carName,company);
        return Response.ok(cars).build();
    }
}
