package com.example.shenawynkov.bakingapp.models;

import android.provider.MediaStore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shenawynkov on 7/8/2017.
 */

public class Bake implements Serializable {
    public Bake() {
    }

    public List<ingredients> getIngredientses() {
        return ingredientses;
    }

    public List<steps> getStepses() {
        return stepses;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<ingredients> ingredientses =new ArrayList<>();
    private List<steps> stepses=new ArrayList<>();

    public void addIngredient(String measure, String ingredient, int quantity) {
        ingredientses.add(new ingredients(measure,ingredient,quantity));
    }

    public void addStep(int id, String shortDescription, String description, String thumbnailURL, String videoUrl)
    {
     stepses.add(new steps(id,shortDescription,description,thumbnailURL,videoUrl));
    }


    public class ingredients  implements Serializable{
        public ingredients(String measure, String ingredient, int quantity) {
            this.measure = measure;
            this.ingredient = ingredient;
            this.quantity = quantity;
        }

        private String measure, ingredient;
        private int quantity;

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
   public  class steps implements Serializable{
       int id;

       public steps(int id, String shortDescription, String description, String thumbnailURL, String videoUrl) {
           this.id = id;
           this.shortDescription = shortDescription;
           this.description = description;
           this.thumbnailURL = thumbnailURL;
           this.videoUrl = videoUrl;
       }

       private String shortDescription, description, thumbnailURL;
       private String videoUrl;

       public int getId() {
           return id;
       }

       public void setId(int id) {
           this.id = id;
       }

       public String getShortDescription() {
           return shortDescription;
       }

       public void setShortDescription(String shortDescription) {
           this.shortDescription = shortDescription;
       }

       public String getDescription() {
           return description;
       }

       public void setDescription(String description) {
           this.description = description;
       }

       public String getThumbnailURL() {
           return thumbnailURL;
       }

       public void setThumbnailURL(String thumbnailURL) {
           this.thumbnailURL = thumbnailURL;
       }

       public String getVideoUrl() {
           return videoUrl;
       }

       public void setVideoUrl(String videoUrl) {
           this.videoUrl = videoUrl;
       }
   }
}