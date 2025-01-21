package com.gilandrey.dslist.services;

import com.gilandrey.dslist.dto.GameDTO;
import com.gilandrey.dslist.dto.GameListDTO;
import com.gilandrey.dslist.dto.GameMinDTO;
import com.gilandrey.dslist.entities.Game;
import com.gilandrey.dslist.entities.GameList;
import com.gilandrey.dslist.repositories.GameListRepository;
import com.gilandrey.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(GameListDTO::new).toList();
    }
}
