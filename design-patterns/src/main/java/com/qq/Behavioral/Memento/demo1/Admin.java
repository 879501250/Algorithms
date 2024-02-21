package com.qq.Behavioral.Memento.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理员类，用于操作记录备忘信息，比如某个版本下的内容信息
 */
public class Admin {
    private int cursorIdx = 0;
    private List<ConfigMemento> mementoList = new ArrayList<ConfigMemento>();
    private Map<String, ConfigMemento> mementoMap = new ConcurrentHashMap<String, ConfigMemento>();

    /**
     * 存放
     *
     * @param memento
     */
    public void append(ConfigMemento memento) {
        mementoList.add(memento);
        mementoMap.put(memento.getConfigFile().getVersionNo(), memento);
        cursorIdx++;
    }

    /**
     * 回滚
     *
     * @return
     */
    public ConfigMemento undo() {
        if (--cursorIdx <= 0) return mementoList.get(0);
        return mementoList.get(cursorIdx);
    }

    /**
     * 撤销回滚
     *
     * @return
     */
    public ConfigMemento redo() {
        if (++cursorIdx > mementoList.size()) return mementoList.get(mementoList.size() - 1);
        return mementoList.get(cursorIdx);
    }

    /**
     * 获取指定的历史版本
     *
     * @param versionNo
     * @return
     */
    public ConfigMemento get(String versionNo) {
        return mementoMap.get(versionNo);
    }
}
