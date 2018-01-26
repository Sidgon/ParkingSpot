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
        name = "reservationApi",
        version = "v1",
        resource = "reservation",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Lavdrim.example.com",
                ownerName = "backend.myapplication.Lavdrim.example.com",
                packagePath = ""
        )
)
public class ReservationEndpoint {

    private static final Logger logger = Logger.getLogger(ReservationEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Reservation.class);
    }

    /**
     * Returns the {@link Reservation} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Reservation} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "reservation/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Reservation get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting Reservation with ID: " + id);
        Reservation reservation = ofy().load().type(Reservation.class).id(id).now();
        if (reservation == null) {
            throw new NotFoundException("Could not find Reservation with ID: " + id);
        }
        return reservation;
    }

    /**
     * Inserts a new {@code Reservation}.
     */
    @ApiMethod(
            name = "insert",
            path = "reservation",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Reservation insert(Reservation reservation) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that reservation.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(reservation).now();
        logger.info("Created Reservation with ID: " + reservation.getId());

        return ofy().load().entity(reservation).now();
    }

    /**
     * Updates an existing {@code Reservation}.
     *
     * @param id          the ID of the entity to be updated
     * @param reservation the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Reservation}
     */
    @ApiMethod(
            name = "update",
            path = "reservation/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Reservation update(@Named("id") Long id, Reservation reservation) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(reservation).now();
        logger.info("Updated Reservation: " + reservation);
        return ofy().load().entity(reservation).now();
    }

    /**
     * Deletes the specified {@code Reservation}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Reservation}
     */
    @ApiMethod(
            name = "remove",
            path = "reservation/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Reservation.class).id(id).now();
        logger.info("Deleted Reservation with ID: " + id);
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
            path = "reservation",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Reservation> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Reservation> query = ofy().load().type(Reservation.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Reservation> queryIterator = query.iterator();
        List<Reservation> reservationList = new ArrayList<Reservation>(limit);
        while (queryIterator.hasNext()) {
            reservationList.add(queryIterator.next());
        }
        return CollectionResponse.<Reservation>builder().setItems(reservationList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(Reservation.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Reservation with ID: " + id);
        }
    }
}