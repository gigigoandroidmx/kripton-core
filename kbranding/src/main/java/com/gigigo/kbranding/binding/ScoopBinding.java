package com.gigigo.kbranding.binding;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.ftinc.scoop.Scoop;
import com.gigigo.kbranding.BindItemDrawable;
import com.gigigo.kbranding.BindItemImage;
import com.gigigo.kbranding.BindItemView;
import com.gigigo.kbranding.BrandingManager;
import com.gigigo.kbranding.Toppings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Godínez Vera - 7/20/2017.
 */
public class ScoopBinding {

    protected List<BindItemView> bindItemViews;
    protected BrandLabel brandLabel;

    public ScoopBinding(Builder builder) {
        this.bindItemViews = builder.getBindItemViews();
        this.brandLabel = builder.getBrandLabel();
    }

    public boolean hasBindItemsView() {
        return bindItemViews != null && !bindItemViews.isEmpty();
    }

    public void update() {
        if (hasBindItemsView() && brandLabel != null) {
            Scoop scoop = Scoop.getInstance();

            for(BindItemView item : this.bindItemViews) {
                int colorInt = -1;

                switch (item.getToppingId()) {
                    case Toppings.COLOR_BASE:
                        colorInt = Color.parseColor(brandLabel.getColorBase());
                        break;
                    case Toppings.COLOR_MAIN:
                        colorInt = Color.parseColor(brandLabel.getColorMain());
                        break;
                    case Toppings.COLOR_SECONDARY:
                        colorInt = Color.parseColor(brandLabel.getColorSecondary());
                        break;
                }

                scoop.update(item.getToppingId(), colorInt);
            }
        }
    }

    public static class Builder {

        protected BrandLabel brandLabel;

        private Object object;
        private Context context;
        private Toolbar toolbar;
        private List<BindItemView> bindItemViews = new ArrayList<>();
        private List<BindItemImage> bindItemImages = new ArrayList<>();
        private List<BindItemDrawable> bindItemDrawables = new ArrayList<>();

        public Builder setObject(Object object) {
            this.object = object;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setToolbar(Toolbar toolbar) {
            this.toolbar = toolbar;
            return this;
        }

        public Builder add(BindItemView bindItem) {
            this.bindItemViews.add(bindItem);
            return this;
        }

        public Builder add(BindItemImage bindItem) {
            this.bindItemImages.add(bindItem);
            return this;
        }

        public Builder add(BindItemDrawable bindItem) {
            this.bindItemDrawables.add(bindItem);
            return this;
        }

        public List<BindItemView> getBindItemViews() {
            return bindItemViews;
        }

        public BrandLabel getBrandLabel() {
            return brandLabel;
        }

        public ScoopBinding build() {
            if(null == context)
                throw new NullPointerException("Context is required");

            this.brandLabel = BrandingManager.getBrandLabel(null);
            setStatusBarColor();

            updateToolbar();
            loadImages();
            loadDrawables();

            Scoop scoop = Scoop.getInstance();

            if (this.bindItemViews != null && !this.bindItemViews.isEmpty()) {
                for (BindItemView item : this.bindItemViews) {
                    if(item.getView() != null) {
                        scoop.bind(object,
                                item.getToppingId(),
                                item.getView(),
                                item.getColorAdapter());
                    }
                }
            }

            return new ScoopBinding(this);
        }

        private void setStatusBarColor() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(object instanceof AppCompatActivity && brandLabel != null) {
                    Window window = ((AppCompatActivity)object).getWindow();
                    if(window != null) {
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(Color.parseColor(brandLabel.getColorBase()));
                    }
                }
            }
        }

        public boolean hasBindItemImages() {
            return bindItemImages != null && !bindItemImages.isEmpty();
        }

        public boolean hasBindItemDrawables() {
            return bindItemDrawables != null && !bindItemDrawables.isEmpty();
        }

        private void updateToolbar() {
            if(toolbar != null && brandLabel != null) {

                Scoop.getInstance().bind(this, Toppings.COLOR_BASE, toolbar);
                Scoop.getInstance().update(Toppings.COLOR_BASE,
                        Color.parseColor(brandLabel.getColorBase()));
                Drawable drawable = toolbar.getNavigationIcon();
                if(drawable != null) {
                    drawable.setColorFilter(Color.parseColor(brandLabel.getColorSecondary()),
                            PorterDuff.Mode.SRC_ATOP);
                }
            }
        }

        private void loadImages() {
            if(hasBindItemImages() && context != null &&
                    brandLabel != null ) {

                for (BindItemImage item : bindItemImages) {
                    if(item.getImageView() != null) {
                        String logo = brandLabel.getLogoPrincipal();

                        if(item.getToppingId() == Toppings.LOGO_SECONDARY) {
                            logo = brandLabel.getLogoSecondary();
                        }

                        BrandingManager.getEndPoint();

                        String urlLogo = String.format("%s/%s",
                                BrandingManager.getEndPoint(),
                                logo);

                        Glide.with(context)
                                .load(urlLogo)
                                .into(item.getImageView());
                    }
                }
            }
        }

        private void loadDrawables() {
            if(hasBindItemDrawables() && brandLabel != null) {
                for(BindItemDrawable item : this.bindItemDrawables) {
                    if(item.getDrawable() != null) {
                        int colorInt = -1;

                        switch (item.getToppingId()) {
                            case Toppings.COLOR_BASE:
                                colorInt = Color.parseColor(brandLabel.getColorBase());
                                break;
                            case Toppings.COLOR_MAIN:
                                colorInt = Color.parseColor(brandLabel.getColorMain());
                                break;
                            case Toppings.COLOR_SECONDARY:
                                colorInt = Color.parseColor(brandLabel.getColorSecondary());
                                break;
                        }

                        item.getDrawable().setColorFilter(colorInt, PorterDuff.Mode.SRC_ATOP);
                    }
                }
            }
        }

    }

    public static void unbind(@NonNull Object obj) {
        Scoop.getInstance().unbind(obj);
    }
}
