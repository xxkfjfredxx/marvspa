package rueda.fred.marvel.com.marvelspa;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Fred Rueda.
 * Developer Fred Rueda
 * Email fredjruedao@gmail.com
 */

public class MarvelApplication extends com.activeandroid.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

}
