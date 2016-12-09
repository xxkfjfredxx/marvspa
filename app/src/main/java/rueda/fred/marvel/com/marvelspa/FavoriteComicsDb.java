package rueda.fred.marvel.com.marvelspa;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Fred Rueda.
 * Developer Fred Rueda
 * Email fredjruedao@gmail.com
 */
@Table(name = "favoritecomics")
public class FavoriteComicsDb extends Model {
    @Column(name = "idcomic")
    int idComic;
    @Column(name = "title")
    String title;
    @Column(name = "description")
    String description;
    @Column(name = "image")
    String image;

    public FavoriteComicsDb() {
        super();
    }

    public FavoriteComicsDb(int idComic, String title, String description, String image) {
        super();
        this.idComic = idComic;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public int getIdComic() {
        return idComic;
    }

    public void setIdComic(int idComic) {
        this.idComic = idComic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
