package com.example.Lavdrim.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "parkingspotApi",
        version = "v1",
        resource = "parkingspot.android",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Lavdrim.example.com",
                ownerName = "backend.myapplication.Lavdrim.example.com",
                packagePath = ""
        )
)
public class ParkingspotEndpoint {

    private static final Logger logger = Logger.getLogger(ParkingspotEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Parkingspot.class);
    }

    /**
     * Returns the {@link Parkingspot} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Parkingspot} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "parkingspot.android/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Parkingspot get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting Parkingspot with ID: " + id);
        Parkingspot parkingspot = ofy().load().type(Parkingspot.class).id(id).now();
        if (parkingspot == null) {
            throw new NotFoundException("Could not find Parkingspot with ID: " + id);
        }
        return parkingspot;
    }

    /**
     * Inserts a new {@code Parkingspot}.
     */
    @ApiMethod(
            name = "insert",
            path = "parkingspot.android",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Parkingspot insert(Parkingspot parkingspot) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that parkingspot.android.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(parkingspot).now();
        logger.info("Created Parkingspot with ID: " + parkingspot.getId());

        return ofy().load().entity(parkingspot).now();
    }

    /**
     * Updates an existing {@code Parkingspot}.
     *
     * @param id          the ID of the entity to be updated
     * @param parkingspot the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Parkingspot}
     */
    @ApiMethod(
            name = "update",
            path = "parkingspot.android/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Parkingspot update(@Named("id") Long id, Parkingspot parkingspot) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(parkingspot).now();
        logger.info("Updated Parkingspot: " + parkingspot);
        return ofy().load().entity(parkingspot).now();
    }

    /**
     * Deletes the specified {@code Parkingspot}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Parkingspot}
     */
    @ApiMethod(
            name = "remove",
            path = "parkingspot.android/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Parkingspot.class).id(id).now();
        logger.info("Deleted Parkingspot with ID: " + id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "parkingspot.android",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Parkingspot> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Parkingspot> query = ofy().load().type(Parkingspot.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Parkingspot> queryIterator = query.iterator();
        List<Parkingspot> parkingspotList = new ArrayList<Parkingspot>(limit);
        while (queryIterator.hasNext()) {
            parkingspotList.add(queryIterator.next());
        }
        return CollectionResponse.<Parkingspot>builder().setItems(parkingspotList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(Parkingspot.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Parkingspot with ID: " + id);
        }
    }
}