(ns om-commerce.core
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [compassus.core :as compassus]))


(defmulti read om/dispatch)

(defmethod read :default
  [{:keys [state query]} k _]
  {:value (get @state k)})

(defui Home
  static om/IQuery
  (query [this]
    [:home/title :home/content])
  Object
  (render [this]
    (let [{:keys [home/title home/content]} (om/props this)]
      (dom/div nil
               (dom/h3 nil title)
               (dom/p nil (str content))))))

(defui About
  static om/IQuery
  (query [this]
    [:about/title :about/content])
  Object
  (render [this]
    (let [{:keys [about/title about/content]} (om/props this)]
      (dom/div nil
               (dom/h3 nil title)
               (dom/p nil (str content))))))

(defn- change-route [c route e]
  (.preventDefault e)
  (compassus/set-route! c route))

(defn wrapper [{:keys [owner factory props]}]
  (let [route (compassus/current-route owner)]
    (dom/div #js {:style #js {:margin "0 auto"
                              :height 250
                              :width 500
                              :backgroundColor "oldlace"}}
             (dom/div #js {:style #js {:minWidth "100%"
                                       :minHeight "48px"
                                       :lineHeight "48px"
                                       :verticalAlign "middle"
                                       :borderBottomWidth "2px"
                                       :borderBottomStyle "solid"}}
                      (dom/h2 #js {:style #js {:margin 0
                                               :textAlign "center"
                                               :lineHeight "48px"}}
                              "Om Commerce"))
             (dom/div #js {:style #js {:display "inline-block"
                                       :width "25%"
                                       :minHeight "80%"
                                       :verticalAlign "top"
                                       :backgroundColor "gainsboro"}}
                      (dom/ul nil
                              (dom/li #js {:style #js {:marginTop "20px"}}
                                      (dom/a #js {:href "#"
                                                  :style (when (= route :app/home)
                                                           #js {:color "black"
                                                                :cursor "text"})
                                                  :onClick #(change-route owner :app/home %)}
                                             "Home"))
                              (dom/li #js {:style #js {:marginTop "5px"}}
                                      (dom/a #js {:href "#"
                                                  :style (when (= route :app/about)
                                                           #js {:color "black"
                                                                :cursor "text"})
                                                  :onClick #(change-route owner :app/about %)}
                                             "About")))
                      (dom/p #js {:style #js {:textAlign "center"
                                              :textDecoration "underline"
                                              :marginBottom "5px"
                                              :marginTop "30px"
                                              :fontWeight "bold"}}
                             "Current route:")
                      (dom/p #js {:style #js {:textAlign "center"
                                              :margin 0
                                              :color "red"}}
                             (str (pr-str route))))
             (dom/div #js {:style #js {:display "inline-block"
                                       :width "70%"
                                       :minHeight "70%"
                                       :verticalAlign "top"
                                       :padding "12.5px 12.5px 12.5px 10.5px"
                                       :borderLeftWidth "2px"
                                       :borderLeftStyle "solid"}}
                      (factory props)))))

(def app-state
         {:app/home {:home/title "Om Commerce Home"
                     :home/content "Thank you for shopping Om Commerce. We don't sell anything yet"}
          :app/about {:about/title "About Us"
                      :about/content "Om Commerce is just one man with a vision to sell things on the internet and not feel like he wants to pull his hair out writing the ui code"}})

(def app
  (compassus/application {:routes {:app/home (compassus/index-route Home)
                           :app/about About}
                  :wrapper wrapper
                  :reconciler-opts {:state (atom app-state)
                                    :parser (om/parser {:read read})}}))

(compassus/mount! app (gdom/getElement "app"))
