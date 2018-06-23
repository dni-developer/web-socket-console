package net.dni.websocketconsole.controller;

import net.dni.websocketconsole.model.LogMessage;
import net.dni.websocketconsole.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/console")
public class ConsoleRestController {

    private final ConsoleService consoleService;

    @Autowired
    public ConsoleRestController(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String search(@RequestParam("message")String content) {
        return consoleService.search(content);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String add(@RequestBody LogMessage input) {
        return consoleService.add(input);
    }
}
