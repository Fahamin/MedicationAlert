package medication.takemedichine.medicationalert.Utils;

import android.util.SparseArray;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

class WeakHolderTracker {
    private SparseArray<WeakReference<SelectableHolder>> mHoldersByPosition = new SparseArray();

    WeakHolderTracker() {
    }

    public SelectableHolder getHolder(int position) {
        WeakReference<SelectableHolder> holderRef = (WeakReference)this.mHoldersByPosition.get(position);
        if (holderRef == null) {
            return null;
        } else {
            SelectableHolder holder = (SelectableHolder)holderRef.get();
            if (holder != null && holder.getAdapterPosition() == position) {
                return holder;
            } else {
                this.mHoldersByPosition.remove(position);
                return null;
            }
        }
    }

    public void bindHolder(SelectableHolder holder, int position) {
        this.mHoldersByPosition.put(position, new WeakReference(holder));
    }

    public List<SelectableHolder> getTrackedHolders() {
        List<SelectableHolder> holders = new ArrayList();

        for(int i = 0; i < this.mHoldersByPosition.size(); ++i) {
            int key = this.mHoldersByPosition.keyAt(i);
            SelectableHolder holder = this.getHolder(key);
            if (holder != null) {
                holders.add(holder);
            }
        }

        return holders;
    }
}

