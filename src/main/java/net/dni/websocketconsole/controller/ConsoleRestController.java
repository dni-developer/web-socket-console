package net.dni.websocketconsole.controller;

import net.dni.websocketconsole.model.Search;
import net.dni.websocketconsole.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/console")
public class ConsoleRestController {

    @Autowired
    private ConsoleService consoleService;

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String search(Search search) {
        return consoleService.search(search);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String add(Search search) {
        return consoleService.add(search);
    }
}
