package com.web.autoshow.dao;

import com.web.autoshow.models.Show;
import com.web.autoshow.repositories.ShowRepository;
import org.springframework.stereotype.Component;

@Component
public class ShowDAO {
    private final ShowRepository showRepo;

    public ShowDAO(ShowRepository showRepo) {
        this.showRepo = showRepo;
    }

    public void add(Show show) {
        showRepo.save(show);
    }
}
