package medication.takemedichine.medicationalert.Utils;

public interface SelectableHolder {
    void setSelectable(boolean var1);

    boolean isSelectable();

    void setActivated(boolean var1);

    boolean isActivated();

    int getAdapterPosition();

    long getItemId();
}

