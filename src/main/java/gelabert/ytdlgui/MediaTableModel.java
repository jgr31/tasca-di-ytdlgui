package gelabert.ytdlgui;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Swing table model used to render media items inside the library table.
 */
public class MediaTableModel extends AbstractTableModel {

    private final String[] cols = {"Nombre", "Tamaño (MB)", "MIME", "Fecha"};
    private List<MediaFile> data;

    public MediaTableModel(List<MediaFile> data) {
        this.data = data;
    }

    public void setData(List<MediaFile> newData) {
        this.data = newData;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int col) {
        return cols[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        MediaFile mf = data.get(row);

        return switch (col) {
            case 0 -> mf.getName();
            case 1 -> String.format("%.02f", mf.getSizeBytes() / 1024d / 1024d);
            case 2 -> mf.getMimeType();
            case 3 -> mf.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            default -> "";
        };
    }

    public MediaFile getItemAt(int row) {
        return data.get(row);
    }
}
