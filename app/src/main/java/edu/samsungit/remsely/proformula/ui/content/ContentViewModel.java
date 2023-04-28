package edu.samsungit.remsely.proformula.ui.content;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ContentViewModel extends ViewModel {
    private ContentMakersRepository contentMakersRepository;
    private LiveData<List<ContentAuthorDataModel>> contentAuthorsLiveData;

    public ContentViewModel(){
        contentMakersRepository = new ContentMakersRepository();
        contentAuthorsLiveData = contentMakersRepository.getContentAuthorsLiveData();
    }

    public LiveData<List<ContentAuthorDataModel>> getContentAuthorsLiveData(){
        return contentAuthorsLiveData;
    }
}