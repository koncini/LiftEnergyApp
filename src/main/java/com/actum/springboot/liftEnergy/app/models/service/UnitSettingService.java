package com.actum.springboot.liftEnergy.app.models.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.actum.springboot.liftEnergy.app.models.UnitSetting;

@Component
public class UnitSettingService {

	private List<UnitSetting> settings;

    public Object getValueByName(String name) {
        for (UnitSetting setting : settings) {
            if (setting.getName().equals(name)) {
                return setting.getValue();
            }
        }
        throw new IllegalArgumentException("Setting with name '" + name + "' not found.");
    }
    
    public void setValueByName(String name, Object value) {
        for (UnitSetting setting : settings) {
            if (setting.getName().equals(name)) {
                setting.setValue(value);
                return;
            }
        }
        throw new IllegalArgumentException("Setting with name '" + name + "' not found.");
    }

}
