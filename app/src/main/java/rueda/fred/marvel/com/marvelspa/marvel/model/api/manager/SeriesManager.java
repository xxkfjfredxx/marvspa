package rueda.fred.marvel.com.marvelspa.marvel.model.api.manager;


import retrofit.Callback;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.MarvelApi;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.CharacterRequest;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.ComicRequest;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.CreatorRequest;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.EventRequest;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.SeriesRequest;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.StoryRequest;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.response.ServiceResponse;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.service.Series;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.vo.Comic;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.vo.Creator;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.vo.Event;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.vo.Story;

/**
 * Manager that handles retrieval series information and requests related to a specific series id.
 *
 * Created by Trey Robinson on 2/23/14.
 */
public class SeriesManager extends BaseManager {

    private Series series;

    public SeriesManager(){
        series = MarvelApi.getInstance().getRestAdapter().create(Series.class);
    }

    /**
     * Retrieve all series matching the provided request parameters.
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void listSeries(SeriesRequest request, Callback<ServiceResponse<Series>> callback) {
        series.listSeries(request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getTitle()
                , request.getModifiedSince()
                , parameterizeList(request.getComics())
                , parameterizeList(request.getStories())
                , parameterizeList(request.getEvents())
                , parameterizeList(request.getCreators())
                , parameterizeList(request.getCharacters())
                , request.getSeriesType().getValue()
                , request.getContains().getValue()
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve a series with a specific ID.
     * @param seriesId  Unique ID for the event
     * @param callback Handler called on request completion
     */
    public void getSeriesWithId(int seriesId, CreatorRequest request, Callback<ServiceResponse<Series>> callback) {
        series.getSeriesWithId(seriesId
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , callback);
    }

    /**
     * Retrieve characters for a series with a specific ID.
     * @param seriesId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getCharactersForSeriesId(int seriesId, CharacterRequest request, Callback<ServiceResponse<Character>> callback){
        series.getCharactersForSeriesId(seriesId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getName()
                , request.getModifiedSince()
                , parameterizeList(request.getComics())
                , parameterizeList(request.getEvents())
                , parameterizeList(request.getStories())
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve comics for a series with a specific ID.
     * @param seriesId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getComicsForEventId(int seriesId, ComicRequest request, Callback<ServiceResponse<Comic>> callback) {
        series.getComicsForSeriesId(seriesId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getFormat().getValue()
                , request.getFormatType().getValue()
                , request.isNoVariants()
                , request.getDateDescriptor().getValue()
                , request.getDateRange()
                , request.isHasDigitalIssue()
                , request.getModifiedSince()
                , parameterizeList(request.getCreators())
                , parameterizeList(request.getCharacters())
                , parameterizeList(request.getEvents())
                , parameterizeList(request.getStories())
                , parameterizeList(request.getSharedAppearances())
                , parameterizeList(request.getCollaborators())
                , request.getOrderBy().getValue(), callback);
    }

    /**
     * Retrieve creators for a series with a specific ID.
     * @param seriesId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getCreatorsForSeriesId(int seriesId, CreatorRequest request, Callback<ServiceResponse<Creator>> callback){
        series.getCreatorsForSeriesId(seriesId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getFirstName()
                , request.getMiddleName()
                , request.getLastName()
                , request.getSuffix()
                , request.getModifiedSince()
                , parameterizeList(request.getComics())
                , parameterizeList(request.getEvents())
                , parameterizeList(request.getStories())
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve events for a series with a specific ID.
     * @param seriesId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getEventsForSeriesId(int seriesId, EventRequest request, Callback<ServiceResponse<Event>> callback) {
        series.getEventsForSeriesId(seriesId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getName()
                , request.getModifiedSince()
                , parameterizeList(request.getComics())
                , parameterizeList(request.getCharacters())
                , parameterizeList(request.getCreators())
                , parameterizeList(request.getStories())
                , request.getOrderBy().getValue()
                , callback);
    }

    /**
     * Retrieve stories for a series with a specific ID.
     * @param seriesId  Unique ID for the event
     * @param request Parameters for the request
     * @param callback Handler called on request completion
     */
    public void getStoriesForComicId(int seriesId, StoryRequest request, Callback<ServiceResponse<Story>> callback) {
        series.getStoriesForEventId(seriesId
                , request.getLimit()
                , request.getOffset()
                , String.valueOf(request.getTimestamp())
                , request.getApiKey()
                , request.getHashSignature()
                , request.getModifiedSince()
                , parameterizeList(request.getEvents())
                , parameterizeList(request.getComics())
                , parameterizeList(request.getCreators())
                , parameterizeList(request.getCharacters())
                , request.getOrderBy().getValue()
                , callback);
    }
}
