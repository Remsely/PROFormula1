package edu.samsungit.remsely.proformula.ui.content;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.ContentAuthorDataModel;
import edu.samsungit.remsely.proformula.data.repositories.ContentMakersRepository;

public class ContentViewModel extends ViewModel {
    private final LiveData<List<ContentAuthorDataModel>> contentAuthorsLiveData;

    public ContentViewModel(){
        ContentMakersRepository contentMakersRepository = new ContentMakersRepository();
        contentAuthorsLiveData = contentMakersRepository.getContentAuthorsLiveData();
    }

    public LiveData<List<ContentAuthorDataModel>> getContentAuthorsLiveData(){
        return contentAuthorsLiveData;
    }
}