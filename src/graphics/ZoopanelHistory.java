package graphics;

import java.util.Stack;

public class ZoopanelHistory {
    private ZooPanel panelHistory;
    private History history = new History();
    public ZoopanelHistory(ZooPanel window) { this.panelHistory = window; }
    private class History {
        private Stack<Memento> m_undo = new Stack <>();
        public void save() {
            if(m_undo.size() < 3)
                m_undo.push(panelHistory.save());
            else
                panelHistory.notifyFullHistory();
        }
        public void undo() {
            if(m_undo.size()>0)
                panelHistory.restore(m_undo.pop());
            else
                panelHistory.notifyEmptyHistory();
        }
    }
    public void hitSave()
    {
        history.save();
    }
    public void hitUndo()
    {
        history.undo();
    }


}