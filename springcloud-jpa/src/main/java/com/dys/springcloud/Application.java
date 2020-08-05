package com.dys.springcloud;

import com.dys.springcloud.entity.BaseAdminFunction;
import com.dys.springcloud.entity.BaseAdminFunctionMenuButton;
import com.dys.springcloud.repository.BaseAdminFunctionMenuButtonRepository;
import com.dys.springcloud.repository.BaseAdminFunctionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dingyingsi
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Resource
    private BaseAdminFunctionMenuButtonRepository baseAdminFunctionMenuButtonRepository;

    @Resource
    private BaseAdminFunctionRepository baseAdminFunctionRepository;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {

        List<BaseAdminFunctionMenuButton> baseAdminFunctionMenuButtons = baseAdminFunctionMenuButtonRepository.findAll();
        List<Long> functionIds = baseAdminFunctionMenuButtons.stream().map(BaseAdminFunctionMenuButton::getFunctionId).distinct().collect(Collectors.toList());

        List<DataObject> dataObjects = new ArrayList<>();

        for (Long functionId : functionIds) {
            List<BaseAdminFunctionMenuButton> baseAdminFunctionMenuButtonsByFunctionId = this.baseAdminFunctionMenuButtonRepository.findByFunctionId(functionId);

            Set<Long> menuIds = baseAdminFunctionMenuButtonsByFunctionId.stream().map(BaseAdminFunctionMenuButton::getMenuId).collect(Collectors.toSet());
            List<Long> mIds = new ArrayList<>(menuIds);
            mIds.sort(Comparator.naturalOrder());

            Set<Long> buttonIds = baseAdminFunctionMenuButtonsByFunctionId.stream().map(BaseAdminFunctionMenuButton::getButtonId).collect(Collectors.toSet());
            List<Long> bIds = new ArrayList<>(buttonIds);
            bIds.sort(Comparator.naturalOrder());

            DataObject dataObject = new DataObject();
            dataObject.setFunctionId(functionId);
            dataObject.setMenuIds(mIds);
            dataObject.setButtonIds(bIds);

            dataObjects.add(dataObject);
        }

        

        Map<String, TreeSet> result = new HashMap<>();

        Set<Long> functionIdDuplexs = new HashSet<>();
        
       for (DataObject dataObject : dataObjects) {
           for (DataObject dataO : dataObjects) {
               if (!dataObject.getFunctionId().equals(dataO.getFunctionId()) && dataObject.getButtonIds().toString().equalsIgnoreCase(dataO.getButtonIds().toString()) && (!dataObject.getButtonIds().toString().equalsIgnoreCase("[847277619947300]"))) {
                   // System.out.println(dataObject.getButtonIds().toString() + " == " + dataO.getButtonIds().toString() + ", " + dataObject.getFunctionId() + ", " + dataO.getFunctionId());
                   //System.out.println(dataObject.getFunctionId() + ", " + dataO.getFunctionId());

                   BaseAdminFunction f1 = baseAdminFunctionRepository.findByFunctionId(dataObject.getFunctionId());
                   BaseAdminFunction f2 = baseAdminFunctionRepository.findByFunctionId(dataO.getFunctionId());

                   functionIdDuplexs.add(f1.getFunctionId());
                   functionIdDuplexs.add(f2.getFunctionId());

                   System.out.println("-----------------------------------------------");
                   System.out.println(dataObject.getButtonIds().toString());
                   System.out.println(dataO.getButtonIds().toString());
                   System.out.println(f1.getName() + " : " +f2.getName());
                   System.out.println("-----------------------------------------------");

                   if (result.get(dataO.getButtonIds().toString()) == null) {
                       TreeSet<String> names = new TreeSet<>();
                       names.add(f1.getName());
                       names.add(f2.getName());
                       result.put(dataO.getButtonIds().toString(), names);
                   } else {
                       TreeSet<String> names = result.get(dataO.getButtonIds().toString());
                       names.add(f1.getName());
                       names.add(f2.getName());
                   }
               }
           }
       }

        List<TreeSet> collect = result.values().stream().collect(Collectors.toList());

       List<BaseAdminFunction> baseAdminFunctions = this.baseAdminFunctionRepository.findByFunctionIdNotIn(new ArrayList<>(functionIdDuplexs));
        List<String> ns = baseAdminFunctions.stream().map(BaseAdminFunction::getName).collect(Collectors.toList());
        System.out.println(ns);

        System.out.println(objectMapper.writeValueAsString(collect));

    }
}
