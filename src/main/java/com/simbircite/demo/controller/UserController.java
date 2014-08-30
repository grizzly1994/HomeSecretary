package com.simbircite.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;

import com.google.common.collect.Lists;
import com.simbircite.demo.form.Message;
import com.simbircite.demo.model.User;
import com.simbircite.demo.model.UserGrid;
import com.simbircite.demo.service.UserService;
import com.simbircite.demo.util.DateUtil;
import com.simbircite.demo.util.UrlUtil;
import com.simbircite.demo.util.spring.CustomDateTimeEditor;
import com.simbircite.demo.validator.UserValidator;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Пример контроллера Spring. 
 * 
 * В Spring MVC существует целая иерархия контроллеров, 
 * базовый интерфейс среди которых, - Controller. 
 * 
 * Все контроллеры в Spring MVC реализуют этот интерфейс. 
 * Однако нам реализовывать его не нужно будет. 
 * 
 * В Spring MVC есть несколько контроллеров для разных видов запросов и ответов.
 * Самые используемые - это AbstractController, SimpleFormController и MultiActionController.
 * 
 * Отображение URL на представления:
 * 
 * /users                 GET    list()            Список пользователей
 * /users/{id}            GET    show()            Карточка пользователя
 * /users/{id}?form       GET    updateForm()      Форма редактирования карточки
 * /users/{id}?form       POST   update()          Обновление карточки пользователя
 * /users/{id}            DELETE remove()          Удаление карточки пользователя
 * /users?form            GET    createForm()      Форма создания карточки
 * /users?form            POST   create()          Создание карточки пользователя
 * /users/photo/{id}      GET    downloadPhoto()   Загрузка фотографии
 */
@Controller /* объявляем, что данный класс является контроллером */
@RequestMapping("/users")
public class UserController {
    
    private static final String CONTROLLER_PATH = "users/";
    private static final Logger logger = Logger.getLogger(UserController.class);

    /* внедряем бин сервиса для работы с пользователями */
    @Autowired 
    private UserService userService;
    
    /* внедряем бин валидатора модели пользователя */
    @Autowired 
    private UserValidator userValidator;
    
    /* внедряем бин для извлечения сообщений с поддержкой интернационализации */
    @Autowired
    private MessageSource messageSource; 

    /**
     * Метод получения списка пользователей.
     * Вызывается при обращении к URL user при помощи метода GET
     * (http://localhost:8080/SpringJpaDemo/users)
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list() {
        /* Возвращает вид с логическим именем users.
         * В этот вид передаются данные:
         * - "users" список всех пользователей
         */ 
        ModelAndView mav = new ModelAndView();
        mav.setViewName(route("list"));
        mav.addObject("users", userService.getAll());
        return mav;
    }

    @RequestMapping(
            value = "/listgrid", 
            method = RequestMethod.GET, 
            produces = "application/json")
    @ResponseBody
    public UserGrid listGrid(
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "rows", required = false) Integer rows,
        @RequestParam(value = "sidx", required = false) String sortBy,
        @RequestParam(value = "sord", required = false) String order
    ) {
        Sort sort = null;
        String orderBy = sortBy;
        if ((orderBy != null) && orderBy.equals("birthString")) {
            orderBy = "birth";
        }
        if ((orderBy != null) && (order != null)) {
            if (order.equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            } else {
                sort = new Sort(Sort.Direction.ASC, orderBy);
            }
        }
        PageRequest pageRequest = null;
        if (sort != null) {
            pageRequest = new PageRequest(page - 1, rows, sort);
        } else {
            pageRequest = new PageRequest(page - 1, rows);
        }
        Page<User> userPage = userService.getAllByPage(pageRequest);
        
        UserGrid userGrid = new UserGrid();
        userGrid.setCurrentPage(userPage.getNumber() + 1);
        userGrid.setTotalPages(userPage.getTotalPages());
        userGrid.setTotalRecords(userPage.getTotalElements()); 
        userGrid.setUserData(Lists.newArrayList(userPage.iterator()));
        
        return userGrid;
    }
    /**
     * Переход к форме просмотра пользователя с идентификатором id.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return route("show");
    }

    /**
     * Переход к форме редактирования пользователя с идентификатором id.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return route("update");
    }

    /**
     * Метод для сохранения параметров существующего пользователя.
     * Вызывается при сабмите формы.
     * Перед сохранением параметров происходит проверка корректности данных.
     * Если ошибок нет, то происходит переход на форму просмотра пользователя.
     * Если есть, то выводится сообщения об ошибках.
     * 
     * Параметры модели пользователя заполняются автоматически
     * атрибутами модели вида (формы) с именем "user".
     */
    @RequestMapping(value = "{id}", params = "form", method = RequestMethod.POST)
    public String update(
            @Valid @ModelAttribute("user") User user, 
            BindingResult bindingResult,
            Model uiModel,
            HttpServletRequest httpServletRequest,
            RedirectAttributes redirectAttributes,
            Locale locale,
            @RequestParam(value = "photoFile", required = false) Part photoFile
            ) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error",
                    messageSource.getMessage("users.save.fail", new Object[] {}, locale)));
            uiModel.addAttribute("user", user);
            return route("update");
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("users.save.success", new Object[] {}, locale)));

        setUserPhoto(user, photoFile);
        userService.update(user);

        return "redirect:/" + route("")
                + UrlUtil.encodeUrlPathSegment(user.getId().toString(), httpServletRequest);
    }

    /**
     * Переход к форме создания нового пользователя.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return route("create");
    }

    /**
     * Метод для создания нового пользователя.
     * Вызывается при сабмите формы.
     * Перед сохранением параметров происходит проверка корректности данных.
     * Если ошибок нет, то происходит переход на форму просмотра пользователя.
     * Если есть, то выводится сообщения об ошибках.
     * 
     * Параметры модели пользователя заполняются автоматически
     * атрибутами модели вида (формы) с именем "user".
     */
    @RequestMapping(params="form", method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("user") User user, 
            BindingResult bindingResult,
            Model uiModel,
            HttpServletRequest httpServletRequest,
            RedirectAttributes redirectAttributes,
            Locale locale,
            @RequestParam(value = "photoFile", required = false) Part photoFile
            ) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error",
                    messageSource.getMessage("users.save.fail", new Object[] {}, locale)));
            uiModel.addAttribute("user", user);
            return route("create");
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("users.save.success", new Object[] {}, locale)));
        
        setUserPhoto(user, photoFile);
        userService.update(user);

        return "redirect:/" + route("")
                + UrlUtil.encodeUrlPathSegment(user.getId().toString(), httpServletRequest);
    }

    /**
     * Удаление карточки пользователя.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String remove(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes,
            Locale locale
            ) {
        User user = userService.getById(id);
        if (user != null) {
            userService.delete(user);
            redirectAttributes.addFlashAttribute("message", new Message("success",
                    messageSource.getMessage("users.remove.success", new Object[] {}, locale)));
        }
        return "redirect:/" + route("");
    }
    
    /** Загрузка фотографии из карточки пользователя */
    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] downloadPhoto(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return user.getPhoto();
    }
    
    /**
     * Обработчик даты, введенной пользователем на странице формы
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DateUtil.getDateFormat());
        binder.registerCustomEditor(
                DateTime.class, new CustomDateTimeEditor(formatter));
    }

    private void setUserPhoto(User user, Part photoFile) {
        if (photoFile != null) {
            byte[] photoFileContent = null;
            try {
                InputStream inputStream = photoFile.getInputStream();
                if (inputStream != null) {
                    photoFileContent = IOUtils.toByteArray(inputStream);
                    user.setPhoto(photoFileContent);
                }
            } catch (IOException ex) {
                logger.error("Error saving uploaded file");
            }
        }
    }
    
    private String route(String viewName) {
        return CONTROLLER_PATH + viewName; 
    }
}